package domain;

import java.util.Date;

/**
 * Sale Domain implementation.
 * 
 * @author Aleksei_Ivshin
 *
 */
public class Sales{

	/**
	 * Sale id.
	 */
	private int id;
	/**
	 * Car sold.
	 */
	private Car car;
	/**
	 * Merchant, who sale car.
	 */
	private Merchant merchant;
	/**
	 * Customer, who buy car.
	 */
	private Customer customer;
	/**
	 * Car price.
	 */
	private float price;
	/**
	 * Date of sale.
	 */
	private Date saleDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

}
