package com.ericsson.entry;

/**
 * Address 实体
 * 
 * @version ab-0.0.1
 * @author XCF email:xcfhr@126.com
 * @since JDK 1.6
 */
public class Address {
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 手机号码
	 */
	private String mobileNo;
	/**
	 * 家庭地址
	 */
	private String homeAddress;

	/**
	 * 构造函数，由三个参数可构造Address实体
	 * 
	 * @param name
	 *            名字
	 * @param mobileNo
	 *            手机号码
	 * @param homeAddress
	 *            家庭地址
	 */
	public Address(String name, String mobileNo, String homeAddress) {
		super();
		this.name = name;
		this.mobileNo = mobileNo;
		this.homeAddress = homeAddress;
	}

	/**
	 * 返回name值
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 根据参数name，设定当前对象对应的属性name值
	 * 
	 * @param name
	 *            名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回mobile值
	 * 
	 * @return mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * 根据参数mobileNo,设定当前对象对应的属性mobileNo值
	 * 
	 * @param mobileNo
	 *            手机号码
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * 返回homeAddress值
	 * 
	 * @return homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * 根据参数homeAddress,设定当前对象对应的属性homeAddress值
	 * 
	 * @param homeAddress
	 *            家庭地址
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
}
