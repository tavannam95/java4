package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date createat;

	private String image;

	private String name;

	private double price;

	private int quantity;

	private int status;

	@Temporal(TemporalType.DATE)
	private Date updateat;

	//bi-directional many-to-one association to Cart
	@OneToMany(mappedBy="product")
	private List<Cart> carts;

	//bi-directional many-to-one association to Detailorder
	@OneToMany(mappedBy="product")
	private List<Detailorder> detailorders;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="cateid")
	private Category category;

	//bi-directional many-to-many association to Color
	@ManyToMany
	@JoinTable(
		name="productcolor"
		, joinColumns={
			@JoinColumn(name="productid")
			}
		, inverseJoinColumns={
			@JoinColumn(name="colorid")
			}
		)
	private List<Color> colors;

	//bi-directional many-to-many association to Size
		@ManyToMany
		@JoinTable(
			name="productsize"
			, joinColumns={
					@JoinColumn(name="productid")
				}
			, inverseJoinColumns={
					@JoinColumn(name="sizeid")
				
				}
			)
		private List<Size> sizes;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateat() {
		return this.createat;
	}

	public void setCreateat(Date createat) {
		this.createat = createat;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getUpdateat() {
		return this.updateat;
	}

	public void setUpdateat(Date updateat) {
		this.updateat = updateat;
	}

	public List<Cart> getCarts() {
		return this.carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public Cart addCart(Cart cart) {
		getCarts().add(cart);
		cart.setProduct(this);

		return cart;
	}

	public Cart removeCart(Cart cart) {
		getCarts().remove(cart);
		cart.setProduct(null);

		return cart;
	}

	public List<Detailorder> getDetailorders() {
		return this.detailorders;
	}

	public void setDetailorders(List<Detailorder> detailorders) {
		this.detailorders = detailorders;
	}

	public Detailorder addDetailorder(Detailorder detailorder) {
		getDetailorders().add(detailorder);
		detailorder.setProduct(this);

		return detailorder;
	}

	public Detailorder removeDetailorder(Detailorder detailorder) {
		getDetailorders().remove(detailorder);
		detailorder.setProduct(null);

		return detailorder;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Color> getColors() {
		return this.colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}

	public List<Size> getSizes() {
		return this.sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}

}