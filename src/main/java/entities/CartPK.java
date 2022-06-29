package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the carts database table.
 * 
 */
@Embeddable
public class CartPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int productid;

	@Column(insertable=false, updatable=false)
	private int userid;

	public CartPK() {
	}
	public int getProductid() {
		return this.productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getUserid() {
		return this.userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CartPK)) {
			return false;
		}
		CartPK castOther = (CartPK)other;
		return 
			(this.productid == castOther.productid)
			&& (this.userid == castOther.userid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productid;
		hash = hash * prime + this.userid;
		
		return hash;
	}
}