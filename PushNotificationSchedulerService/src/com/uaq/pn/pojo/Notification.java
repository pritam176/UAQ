package com.uaq.pn.pojo;

public class Notification {

		protected Integer badge;
		
		protected String sound;
		
		protected boolean icon;
		
		protected String body;
		
		protected String text;
		
		protected String deviceTocken;
		
		protected String messageId;
		
		protected String payload;
		
		
		/**
		 * @return the payload
		 */
		public String getPayload() {
			return payload;
		}
		/**
		 * @param payload the payload to set
		 */
		public void setPayload(String payload) {
			this.payload = payload;
		}
		/**
		 * @return the badge
		 */
		public Integer getBadge() {
			return badge;
		}
		/**
		 * @param badge the badge to set
		 */
		public void setBadge(Integer badge) {
			this.badge = badge;
		}
		/**
		 * @return the sound
		 */
		public String getSound() {
			return sound;
		}
		/**
		 * @param sound the sound to set
		 */
		public void setSound(String sound) {
			this.sound = sound;
		}
		/**
		 * @return the body
		 */
		public String getBody() {
			return body;
		}
		/**
		 * @param body the body to set
		 */
		public void setBody(String body) {
			this.body = body;
		}
		/**
		 * @return the tocken
		 */
		public String getTocken() {
			return deviceTocken;
		}
		/**
		 * @param tocken the tocken to set
		 */
		public void setTocken(String tocken) {
			this.deviceTocken = tocken;
		}
		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}
		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
		/**
		 * @return the deviceTocken
		 */
		public String getDeviceTocken() {
			return deviceTocken;
		}
		/**
		 * @param deviceTocken the deviceTocken to set
		 */
		public void setDeviceTocken(String deviceTocken) {
			this.deviceTocken = deviceTocken;
		}
		/**
		 * @return the icon
		 */
		public boolean isIcon() {
			return icon;
		}
		/**
		 * @param icon the icon to set
		 */
		public void setIcon(boolean icon) {
			this.icon = icon;
		}
		/**
		 * @return the messageId
		 */
		public String getMessageId() {
			return messageId;
		}
		/**
		 * @param messageId the messageId to set
		 */
		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}
	
		@Override
		public String toString() {
			return "Notification [badge=" + badge + ", sound=" + sound + ", icon=" + icon + ", body=" + body + ", text=" + text + ", deviceTocken="
					+ deviceTocken + ", messageId=" + messageId + ", payload=" + payload + "]";
		}
		
		
				
}
