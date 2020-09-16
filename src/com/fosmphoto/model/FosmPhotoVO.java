package com.fosmphoto.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class FosmPhotoVO implements Serializable{
		private String phoNo;
		private String fosmNo;
		private byte[] phoCon;
		private Timestamp phoTime;
		public String getPhoNo() {
			return phoNo;
		}
		public void setPhoNo(String phoNo) {
			this.phoNo = phoNo;
		}
		public String getFosmNo() {
			return fosmNo;
		}
		public void setFosmNo(String fosmNo) {
			this.fosmNo = fosmNo;
		}
		public byte[] getPhoCon() {
			return phoCon;
		}
		public void setPhoCon(byte[] phoCon) {
			this.phoCon = phoCon;
		}
		public Timestamp getPhoTime() {
			return phoTime;
		}
		public void setPhoTime(Timestamp phoTime) {
			this.phoTime = phoTime;
		}
}
