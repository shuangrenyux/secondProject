package com.ericsson.app;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ericsson.entry.Address;
import com.ericsson.util.HandleXml;

/**
 * 主程序类，程序入口
 * 
 * @version ab-0.0.1
 * @author XCF email:xcfhr@126.com
 * @since JDK 1.6
 */
public class App implements Runnable {
	private static final String cmdLine = "ab> ";
	private static final String filename = "AddressBook.xml";
	public static int flag = 0;

	/**
	 * 用于接收输入的命令
	 */
	public String command;
	/**
	 * Scanner对象， 用于接收输入流
	 */
	public Scanner scanner;

	/**
	 * 主方法，程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建缓存线程池
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Runnable commandR = new App();
		threadPool.execute(commandR);
		threadPool.shutdown();
	}

	/**
	 * 初始化命令行提示符， 并准备开始接收命令
	 * 
	 */
	public void init() {
		System.out.print(cmdLine);
		scanner = new Scanner(System.in);
		command = scanner.nextLine();
	}
	
	public void run() {
		String valueS = "";
		String valueD = "";
		init();
		while (("!quit".equals(command)) == false) {
			if ("add".equals(command)) {

				System.out.print("name: ");
				String name = scanner.nextLine();
				System.out.print("mobile: ");
				String mobileNo = scanner.nextLine();
				if (!mobileNo.matches("^1[358]\\d{9}$")) {
					System.out
							.println("wrong mobile number. Beginning with '13'、'15'、'18'. input again:");
					continue;
				}
				System.out.print("address: ");
				String homeAddress = scanner.nextLine();
				Address addressEntry = new Address(name, mobileNo, homeAddress);
				synchronized (this) {
					HandleXml.add(filename, addressEntry);
				}
				System.out.println("address entry added.");
				init();

			} else if ("search".equals(command)) {
				System.out.print("by (name|mobile|address): ");
				String option = scanner.nextLine();
				if ("name".equals(option)) {
					System.out.print("name: ");
				} else if ("mobile".equals(option)) {
					System.out.print("mobile: ");
				} else if ("address".equals(option)) {
					System.out.print("address: ");
				} else {
					System.out.println("Error command!");
					continue;
				}
				valueS = scanner.nextLine();
				synchronized (this) {
					HandleXml.search(filename, option, valueS);
				}
				init();

			} else if ("delete".equals(command)) {
				System.out.print("by (name|mobile|address): ");
				String option = scanner.nextLine();
				if ("name".equals(option)) {
					System.out.print("name: ");
				} else if ("mobile".equals(option)) {
					System.out.print("mobile: ");
				} else if ("address".equals(option)) {
					System.out.print("address: ");
				} else {
					System.out.println("Error command!");
					continue;
				}
				valueD = scanner.nextLine();
				synchronized (this) {
					HandleXml.delete(filename, option, valueD);
				}
				init();

			} else if ("!help".equals(command)) {
				promptMsg();
				init();

			} else {
				System.out.println("Error command!");
				promptMsg();
				init();
			}
		}
	}

	/**
	 * 显示帮助信息
	 * @return flag
	 */
	int promptMsg() {
		System.out.println("valid commands include:");
		System.out.println("	add|search|delete|!help|!quit");
		System.out.println("add:		add address entry into AddressBook.xml");
		System.out.println("search:		get one or more address entries");
		System.out.println("delete:		delete one or more address entries");
		System.out.println("!help:		help information");
		System.out.println("!quit:		exit");
		flag = 1;
		return flag;
	}

}
