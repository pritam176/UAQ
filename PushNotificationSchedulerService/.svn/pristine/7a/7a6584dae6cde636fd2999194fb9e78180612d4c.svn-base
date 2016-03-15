package com.uaq.pn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.uaq.pn.exception.UAQException;
import com.uaq.pn.pojo.Message;
import com.uaq.pn.util.ConfUtils;

/**
 * This class manipulates data in tables.
 * 
 * @author mraheem
 * 
 */
public class PushNotificationSchedulerDAO {

	
	
	private static final String DB_DRIVER = ConfUtils.getValue("jdbc.driverClassName");
	private static final String DB_CONNECTION = ConfUtils.getValue("jdbc.url");
	private static final String DB_USER = ConfUtils.getValue("jdbc.username");
	private static final String DB_PASSWORD = ConfUtils.getValue("jdbc.password");
	
	private Connection con; 
	
	//used to establish connection with database 
	private Connection getConnection() throws ClassNotFoundException ,SQLException 
	{ 
				 
		try { 
			Class.forName(DB_DRIVER); 
		} catch (ClassNotFoundException e) { 
			System.out.println(e.getMessage()); 
		}
 
		try { 
			con = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD); 
		} catch (SQLException e) { 
			System.out.println(e.getMessage()); 
		}
		 		 
		return con;
	}
	
		
	/**
	 * This method is used to get all pending notifications to be delivered in batch
	 * @return
	 * @throws UAQException
	 */
	public List<Message> getDeliveryNotifications() throws UAQException {
		
		System.out.println("getDeliveryNotifications");		
		
		PreparedStatement ps = null;
		Connection con = null;
		
		List<Message> messages = new ArrayList<Message>();
		
		StringBuilder sql =  new StringBuilder("select m.*, d.devicetoken, d.device_type_id FROM PN_MESSAGE m ");
				sql.append( " join PN_DEVICE d on m.deviceuid = d.deviceuid");
		        sql.append( " WHERE m.message_status_id = 1");
		          
		System.out.println("sql query = " + sql);
		
		try{	
			
			con = getConnection();
			
			if(con != null){
				
				ps = con.prepareStatement(sql.toString());				
								
				ResultSet resultset = ps.executeQuery();			 	 
				
				while (resultset.next()) {
					
					Message message = new Message();					
					message.setMessageId(resultset.getInt("message_id"));
					message.setMessage(resultset.getString("message"));
					message.setDeviceId(resultset.getString("deviceuid"));
					message.setDeviceTocken(resultset.getString("devicetoken"));
					message.setDeviceTypeId(resultset.getString("device_type_id"));
					message.setDate(resultset.getString("custom_date"));
					message.setNotificationTypeId(new Integer(resultset.getString("NOTIFICATION_TYPE_ID")));
					message.setNotificationTypeIdValue(resultset.getString("NOTIFICATION_TYPE_ID_VALUE"));
					message.setUserId(resultset.getString("user_id"));
									
					messages.add(message);
				}
			} else {
				throw new UAQException("database error");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {			
			System.out.println(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		System.out.println("getDeliveryNotifications");
		
		return messages;
		
	}	
	
	/**
	 * This method is used to get the total count of 
	 * private unread notifications of this device user plus public unread notifications of this device 
	 * @param deviceId
	 * @param userId
	 * @return
	 * @throws SQLException 
	 * @throws UAQException 
	 */
	
	public int getNotifications(String deviceId) throws UAQException {
		
		System.out.println("getNotifications");		
		
		Set<Message> messagesSet = new HashSet<Message>(); // to remove duplicates based on userid and message text
		
		PreparedStatement ps = null;
		Connection con = null;
		int result = 0;
			
		StringBuilder sql_unread_count_device_user =  new StringBuilder("");
			sql_unread_count_device_user.append(" select m.message, m.user_id, m.deviceuid, notification_type_id from pn_device d ");
			sql_unread_count_device_user.append(" join pn_message m on d.user_id = m.user_id ");
			sql_unread_count_device_user.append(" where d.deviceuid = ? "); 
			sql_unread_count_device_user.append(" and m.message_status_id = 2 ");
			sql_unread_count_device_user.append(" and m.notification_type_id = 5 ");
			sql_unread_count_device_user.append(" UNION ");
			sql_unread_count_device_user.append(" select m.message, m.user_id, m.deviceuid, notification_type_id from pn_message m ");
			sql_unread_count_device_user.append(" where deviceuid = ? "); 
			sql_unread_count_device_user.append(" and m.message_status_id = 2 ");
			sql_unread_count_device_user.append(" and m.notification_type_id in (2, 3, 4) ");
					
		System.out.println("sql query = " + sql_unread_count_device_user);
		
		try{	
			
			con = getConnection();
			
			if(con != null){
				
				ps = con.prepareStatement(sql_unread_count_device_user.toString());				
				
				ps.setString(1, deviceId);
				ps.setString(2, deviceId);
												
				ResultSet resultset = ps.executeQuery();			 	 
				
				while (resultset.next()) {				
					
					Message message = new Message();
					message.setMessage(resultset.getString("message"));
					message.setUserId(resultset.getString("user_id"));
					message.setNotificationTypeId(resultset.getInt("notification_type_id"));
					
					boolean isInserted = messagesSet.add(message); // this is to remove the duplicates based on userid and message text
					if(isInserted){
						System.out.println("inserted in set");
					} else {
						System.out.println("duplicate found hence not added : messageId = " + message.getMessageId() + " ,userId = " + message.getUserId() 
								+ " : message text = " + message.getMessage());
					}					
				}
				result = messagesSet.size();
			} else {
				throw new UAQException("database error");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {			
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
			
		System.out.println("getNotifications result = " + result);
		
		return result;
		
	}
	
	/**
	 * This method is used to update the message status to read,unread or failed
	 * Also, mark the notification messages for given device
	 * @param messageId
	 * @param status
	 * @return
	 * @throws UAQException
	 * @throws SQLException
	 */
	
	public boolean updateMessage(String deviceId, String messageId, String notificationTypeId, String userId, String status) throws UAQException{
		
		System.out.println("updateMessage messageId = " + messageId + " , status = " + status);		
			
		boolean result = false;
		PreparedStatement ps = null;
		Connection con = null;
		
		StringBuilder query = new StringBuilder("");
		
		query.append("update PN_MESSAGE m set m.message_status_id = ? where m.deviceuid = ? and "); 
		
		if(messageId != null && !messageId.equals("0")){
			query.append("m.message_id in (" + messageId + ")");
		} else if(notificationTypeId != null && !notificationTypeId.equals("0")){
			query.append("m.notification_type_id in (" + notificationTypeId + ")");
			if(userId != null && !userId.equals("0")){
				query.append("join PN_DEVICE d on m.deviceuid = d.deviceuid and d.user_id=" + userId);
			}
		}	
		
		System.out.println("query = " + query);
		
		try {
			
			con = getConnection();
			
			if(con != null){
						
				ps = con.prepareStatement(query.toString()); 	
						
				ps.setInt( 1, Integer.parseInt(status));
				ps.setString( 2, deviceId);	
				
				int updateCount = ps.executeUpdate();
							
				result = updateCount > 0 ? true : false;
			} else {
				throw new UAQException("database error");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {			
			System.out.println(e.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		System.out.println("updateMessage result = " + result);
		
		return result;
		
	}

}
