package com.jiahui.tools;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * 鐢ㄦ埛鏁版嵁搴撹闂殑绫�
 *@浣滆�匒dministrator
 *@createTime 2012-10-22 涓婂崍11:55:18
 *@version 1.0
 */
public class DButil {
	private Connection conn;
	private Statement st;
	private PreparedStatement pps;
	private ResultSet rs;
	public  String url="jdbc:mysql://localhost:3306/lab";
	private String user="root";
	private String password="mysql";
	//鍔犺浇椹卞姩銆佹斁鍦ㄩ潤鎬佷唬鐮佸潡涓紝淇濊瘉椹卞姩鍦ㄦ暣涓」鐩腑鍙姞杞戒竴娆★紝鎻愰珮鏁堢巼
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 鑾峰彇杩炴帴鐨勬柟娉�
	 * @return Connection 涓�涓湁鏁堢殑鏁版嵁搴撹繛鎺�
	 */
	public Connection getConnection()
	{
		try {
			//娉ㄦ剰閾炬帴鏃讹紝瑕佹崲鎴愯嚜宸辩殑鏁版嵁搴撳悕锛屾暟鎹簱鐢ㄦ埛鍚嶅強瀵嗙爜
			Connection con=DriverManager.getConnection(url,user,password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 鐢ㄤ簬鎵ц鏇存柊鐨勬柟娉�,鍖呮嫭锛坕nsert delete update锛夋搷浣�
	 * @param sql String 绫诲瀷鐨凷QL璇彞
	 * @return Integer 琛ㄧず鍙楀奖鍝嶇殑琛屾暟
	 */
	public int update(String sql)
	{
		//瀹氫箟鍙橀噺鐢ㄦ潵鍒ゆ柇鏇存柊鎿嶄綔鏄惁鎴愬姛锛屽鏋滆繑鍥�-1璇存槑娌℃湁褰卞搷鍒版洿鏂版搷浣滅殑鏁版嵁搴撹褰曟潯鏁帮紝鍗虫洿鏂版搷浣滃け璐�
		int row=-1;
		try {
			//濡傛灉鏁版嵁搴撻摼鎺ヨ鍏抽棴浜嗭紝灏辫鏃㈠緱涓�涓柊鐨勯摼鎺�
			if(conn==null||conn.isClosed()){
				 conn=getConnection();
			}
			//浣跨敤Connection瀵硅薄conn鐨刢reateStatement()鍒涘缓Statement锛堟暟鎹簱璇彞瀵硅薄锛塻t
			st=conn.createStatement();
			//鎵ц鏇存柊鎿嶄綔锛岃繑鍥炲奖鍝嶇殑璁板綍鏉℃暟row
			row=st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return row;
	}
	
	/**
	 * 鍩轰簬PreparedStatement鐨勪慨鏀规柟娉� PreparedStatement:琛ㄧず棰勭紪璇戠殑 SQL 璇彞鐨勫璞�
	 * @param sql  String 绫诲瀷鐨凷QL璇彞锛坕nsert delete update锛�
	 * @param obj 瀛樻斁鍔ㄦ�佸弬鏁扮殑鏁扮粍
	 * @return Integer 琛ㄧず鍙楀奖鍝嶇殑琛屾暟
	 */
	public int update(String sql,Object ...obj)
	{
		try {
			//鑾峰彇閾炬帴
			if(conn==null||conn.isClosed()){
				 conn=getConnection();
			}
			//鍒涘缓棰勭紪璇戠殑 SQL 璇彞瀵硅薄
			pps=conn.prepareStatement(sql);
			
			//瀹氫箟鍙橀噺length浠ｈ〃鏁扮粍闀垮害锛屼篃灏辨槸棰勫鐞嗙殑sql璇彞涓殑鍙傛暟涓暟 
			int length=0;
			//ParameterMetaData锛氱敤浜庤幏鍙栧叧浜� PreparedStatement 瀵硅薄涓瘡涓弬鏁扮殑绫诲瀷鍜屽睘鎬т俊鎭殑瀵硅薄
			ParameterMetaData pmd=pps.getParameterMetaData();
			length=pmd.getParameterCount();
			//寰幆灏唖ql璇彞涓殑?璁剧疆涓簅bj鏁扮粍涓搴旂殑鍊硷紝娉ㄦ剰浠�1寮�濮嬶紝鎵�浠瑕佸姞1
			for(int i=0;i<length;i++)
			{
				pps.setObject(i+1, obj[i]);
			}
			//鎵ц鏇存柊鎿嶄綔
			return pps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		
		return -1;
	}
	/**
	 * 鑾峰彇涓�鏉¤褰曠殑鏂规硶锛岃渚濊禆浜庝笅闈㈢殑queryToList鏂规硶锛屾敞鎰忔硾鍨嬬殑浣跨敤
	 * @param sql
	 * @return銆�Map<String,Object>
	 */
	public Map<String,Object> getOneRow(String sql)
	{
		//鎵ц涓嬮潰鐨剄ueryToList鏂规硶
		List<Map<String,Object>> list=queryToList(sql);
		//涓夌洰杩愮畻锛屾煡璇㈢粨鏋渓ist涓嶄负绌鸿繑鍥瀕ist涓涓�涓璞�,鍚﹀垯杩斿洖null
		return list.size()>0?list.get(0):null;
	}
	
	/**
	 * 杩斿洖鏌ヨ缁撴灉鍒楄〃锛屽舰濡傦細[{TEST_NAME=aaa, TEST_NO=2, TEST_PWD=aaa}, {TEST_NAME=bbb, TEST_NO=3, TEST_PWD=bbb}...]
	 * @param sql
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryToList(String sql)
	{
		//鍒涘缓闆嗗悎鍒楄〃鐢ㄤ互淇濆瓨鎵�鏈夋煡璇㈠埌鐨勮褰�
		List<Map<String, Object>> list=new LinkedList<Map<String, Object>>();
		try {
			if(conn==null||conn.isClosed()){
				 conn=getConnection();
			}
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			//ResultSetMetaData 鏄粨鏋滈泦鍏冩暟鎹紝鍙幏鍙栧叧浜� ResultSet 瀵硅薄涓垪鐨勭被鍨嬪拰灞炴�т俊鎭殑瀵硅薄 渚嬪锛氱粨鏋滈泦涓叡鍖呮嫭澶氬皯鍒楋紝姣忓垪鐨勫悕绉板拰绫诲瀷绛変俊鎭�
			ResultSetMetaData rsmd=rs.getMetaData();
			//鑾峰彇缁撴灉闆嗕腑鐨勫垪鏁�
			int columncount=rsmd.getColumnCount();
			//while鏉′欢鎴愮珛琛ㄦ槑缁撴灉闆嗕腑瀛樺湪鏁版嵁
			while(rs.next())
			{
				//鍒涘缓涓�涓狧ashMap鐢ㄤ簬瀛樺偍涓�鏉℃暟鎹�
				HashMap<String, Object> onerow=new HashMap<String, Object>();
				//寰幆鑾峰彇缁撴灉闆嗕腑鐨勫垪鍚嶅強鍒楀悕鎵�瀵瑰簲鐨勫�硷紝姣忔寰幆閮藉緱鍒颁竴涓璞★紝褰㈠锛歿TEST_NAME=aaa, TEST_NO=2, TEST_PWD=aaa}
				for(int i=0;i<columncount;i++)
				{
					//鑾峰彇鎸囧畾鍒楃殑鍚嶇О锛屾敞鎰弌rcle涓垪鍚嶇殑澶у皬鍐�
					String columnName=rsmd.getColumnName(i+1);
					onerow.put(columnName, rs.getObject(i+1));
				}
				//灏嗚幏鍙栧埌鐨勫璞newrow={TEST_NAME=aaa, TEST_NO=2, TEST_PWD=aaa}鏀惧埌闆嗗悎鍒楄〃涓�
				list.add(onerow);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return list;
	}
	/**
	 * 杩斿洖鏌ヨ缁撴灉鍒楄〃,浣跨敤鐨勬槸棰勭紪缁嶴QL 璇彞瀵硅薄PreparedStatement
	 * 褰㈠锛歔{TEST_NAME=aaa, TEST_NO=2, TEST_PWD=aaa}, {TEST_NAME=bbb, TEST_NO=3, TEST_PWD=bbb}]
	 * @param sql
	 * @param paramValues
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> queryWithParam(String sql,Object ...paramValues){
		//鍒涘缓闆嗗悎鍒楄〃鐢ㄤ互淇濆瓨鎵�鏈夋煡璇㈠埌鐨勮褰�
		List<Map<String, Object>> list=new LinkedList<Map<String, Object>>();
		try {
			if(conn==null||conn.isClosed()){
				 conn=getConnection();
			}
			pps = conn.prepareStatement(sql);
			for (int i = 0; i < paramValues.length; i++) {
				pps.setObject(i + 1, paramValues[i]);
			}
			rs = pps.executeQuery();
			//ResultSetMetaData 鏄粨鏋滈泦鍏冩暟鎹紝鍙幏鍙栧叧浜� ResultSet 瀵硅薄涓垪鐨勭被鍨嬪拰灞炴�т俊鎭殑瀵硅薄 渚嬪锛氱粨鏋滈泦涓叡鍖呮嫭澶氬皯鍒楋紝姣忓垪鐨勫悕绉板拰绫诲瀷绛変俊鎭�
			ResultSetMetaData rsmd=rs.getMetaData();
			//鑾峰彇缁撴灉闆嗕腑鐨勫垪鏁�
			int columncount=rsmd.getColumnCount();
			//while鏉′欢鎴愮珛琛ㄦ槑缁撴灉闆嗕腑瀛樺湪鏁版嵁
			while (rs.next()) {
				//鍒涘缓涓�涓狧ashMap鐢ㄤ簬瀛樺偍涓�鏉℃暟鎹�
				HashMap<String, Object> onerow=new HashMap<String, Object>();
				//寰幆鑾峰彇缁撴灉闆嗕腑鐨勫垪鍚嶅強鍒楀悕鎵�瀵瑰簲鐨勫�硷紝姣忔寰幆閮藉緱鍒颁竴涓璞★紝褰㈠锛歿TEST_NAME=aaa, TEST_NO=2, TEST_PWD=aaa}
				for(int i=0;i<columncount;i++)
				{
					//鑾峰彇鎸囧畾鍒楃殑鍚嶇О锛屾敞鎰弌rcle涓垪鍚嶇殑澶у皬鍐�
					String columnName=rsmd.getColumnName(i+1);
					onerow.put(columnName, rs.getObject(i+1));
				}
				//灏嗚幏鍙栧埌鐨勫璞newrow={TEST_NAME=aaa, TEST_NO=2, TEST_PWD=aaa}鏀惧埌闆嗗悎鍒楄〃涓�
				list.add(onerow);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			close();
		}
		return list;
	}
	
	/**
	 * 鍏抽棴鏁版嵁搴撳悇绉嶈祫婧怌onnection Statement PreparedStatement ResultSet鐨勬柟娉�
	 */
	private void close()
	{
	    if(rs!=null)
	    {
	    	try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    
	    if(st!=null)
	    {
	    	try {
	    		st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    if(pps!=null){
	    	try {
	    		pps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	    try {
			if(conn!=null&&!conn.isClosed())
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}