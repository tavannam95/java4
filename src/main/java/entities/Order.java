package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date orderat;

	private String shipaddress;

	private String shipemail;

	private String shipname;

	private String shipphone;

	private int status;

	private double total;

	//bi-directional many-to-one association to Detailorder
	@OneToMany(mappedBy="order")
	private List<Detailorder> detailorders;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Order() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderat() {
		return this.orderat;
	}

	public void setOrderat(Date orderat) {
		this.orderat = orderat;
	}

	public String getShipaddress() {
		return this.shipaddress;
	}

	public void setShipaddress(String shipaddress) {
		this.shipaddress = shipaddress;
	}

	public String getShipemail() {
		return this.shipemail;
	}

	public void setShipemail(String shipemail) {
		this.shipemail = shipemail;
	}

	public String getShipname() {
		return this.shipname;
	}

	public void setShipname(String shipname) {
		this.shipname = shipname;
	}

	public String getShipphone() {
		return this.shipphone;
	}

	public void setShipphone(String shipphone) {
		this.shipphone = shipphone;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Detailorder> getDetailorders() {
		return this.detailorders;
	}

	public void setDetailorders(List<Detailorder> detailorders) {
		this.detailorders = detailorders;
	}

	public Detailorder addDetailorder(Detailorder detailorder) {
		getDetailorders().add(detailorder);
		detailorder.setOrder(this);

		return detailorder;
	}

	public Detailorder removeDetailorder(Detailorder detailorder) {
		getDetailorders().remove(detailorder);
		detailorder.setOrder(null);

		return detailorder;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}