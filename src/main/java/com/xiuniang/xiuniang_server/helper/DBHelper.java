package com.xiuniang.xiuniang_server.helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.xiuniang.xiuniang_server.servlet.DianyuanInfoServlet;

public class DBHelper {
	
	Logger logger = Logger.getLogger(DBHelper.class);
	Connection _CONN = null;  
    
      
    //测试连接  
    public boolean TestConn() {  
        if (!GetConn())  
            return false;  
  
        CloseConn();  
        return true;  
    }  
      
    public ResultSet GetResultSet(String sSQL,Object[] objParams)  
    {  
        GetConn();  
        ResultSet rs=null;  
        try  
        {  
            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
            if(objParams!=null)  
            {  
                for(int i=0;i< objParams.length;i++)  
                {  
                    ps.setObject(i+1, objParams[i]);  
                }  
            }  
            rs=ps.executeQuery();  
        }catch(Exception ex)  
        {  
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage());
            CloseConn();  
        }  
        finally  
        {  
            //CloseConn();            
        }  
        return rs;  
    }  
      
    public Object GetSingle(String sSQL,Object... objParams)  
    {  
        GetConn();  
        try  
        {  
            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
            if(objParams!=null)  
            {  
                for(int i=0;i< objParams.length;i++)  
                {  
                    ps.setObject(i+1, objParams[i]);  
                }  
            }  
            ResultSet rs=ps.executeQuery();  
            if(rs.next())  
                return rs.getString(1);//索引从1开始  
        }catch(Exception ex)  
        {  
            System.out.println(ex.getMessage());
            logger.error(ex.getMessage());
        }  
        finally  
        {  
            CloseConn();              
        }  
        return null;  
    }  
      
//    public DataTable GetDataTable(String sSQL,Object... objParams)  
//    {  
//        GetConn();  
//        DataTable dt=null;  
//        try  
//        {  
//            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
//            if(objParams!=null)  
//            {  
//                for(int i=0;i< objParams.length;i++)  
//                {  
//                    ps.setObject(i+1, objParams[i]);  
//                }  
//            }  
//            ResultSet rs=ps.executeQuery();  
//            ResultSetMetaData rsmd=rs.getMetaData();  
//              
//            List<DataRow> row=new ArrayList<DataRow>(); //表所有行集合  
//            List<DataColumn> col=null; //行所有列集合  
//            DataRow r=null;// 单独一行  
//            DataColumn c=null;//单独一列  
//              
//            String columnName;  
//            Object value;  
//            int iRowCount=0;  
//            while(rs.next())//开始循环读取，每次往表中插入一行记录  
//            {  
//                iRowCount++;  
//                col=new ArrayList<DataColumn>();//初始化列集合  
//                for(int i=1;i<=rsmd.getColumnCount();i++)  
//                {  
//                    columnName=rsmd.getColumnName(i);  
//                    value=rs.getObject(columnName);  
//                    c=new DataColumn(columnName,value);//初始化单元列  
//                    col.add(c); //将列信息加入到列集合  
//                }  
//                r=new DataRow(col);//初始化单元行  
//                row.add(r);//将行信息加入到行集合  
//            }  
//            dt = new DataTable(row);  
//            dt.RowCount=iRowCount;  
//            dt.ColumnCount = rsmd.getColumnCount();  
//        }catch(Exception ex)  
//        {  
//            System.out.println(ex.getMessage());  
//        }  
//        finally  
//        {  
//            CloseConn();              
//        }  
//        return dt;  
//    }  
      
    public int UpdateData(String sSQL,Object[] objParams)  
    {  
        GetConn();  
        int iResult=0;  
          
        try  
        {  
            PreparedStatement ps = _CONN.prepareStatement(sSQL);  
            if(objParams!=null)  
            {  
                for(int i=0;i< objParams.length;i++)  
                {  
                    ps.setObject(i+1, objParams[i]);  
                }  
            }  
            iResult = ps.executeUpdate(sSQL);  
        }catch(Exception ex)  
        {  
            System.out.println(ex.getMessage());  
            return -1;  
        }  
        finally  
        {  
            CloseConn();              
        }  
        return iResult;  
    }  
    
    
    //取得连接  
    private boolean GetConn(String sUser, String sPwd) {  
        if(_CONN!=null)return true;  
        try {             
            String sDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
            String sDBUrl = "jdbc:sqlserver://192.168.1.189;DatabaseName=bserp_rfx";
//            String sDBUrl = "jdbc:sqlserver://58.210.143.138:8044;DatabaseName=bserp_rfx";
            //8044 7011 1433 1434 192.168.1.185
  
            Class.forName(sDriverName);  
            _CONN = DriverManager.getConnection(sDBUrl, sUser, sPwd);  
  
        } catch(SQLException ex){
        	
        	logger.error("SQLException===================");
        	ex.printStackTrace();
        	logger.error(ex.getMessage());
        	logger.error(ex.getErrorCode());
        }
        catch (Exception ex) {  
        	logger.error("Exception===================");
             ex.printStackTrace();
        	logger.error(ex.getMessage());
        	logger.error(ex);
            System.out.println(ex.getMessage());  
            return false;  
        } 
        return true;  
    }  
      
    private boolean GetConn()  
    {  
//        return GetConn("chuliang","chuliang");  
        return GetConn("sa","ruifuxiang");  
    }  
      
    //关闭连接  
    private void CloseConn()  
    {  
        try {  
            _CONN.close();  
            _CONN = null;  
        } catch (Exception ex) {  
            System.out.println(ex.getMessage());  
            _CONN=null;   
        }  
    } 
}
