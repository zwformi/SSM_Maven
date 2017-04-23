package com.zw.test;

interface fruit {
public abstract void eat();
}
class Apple implements fruit {
public void eat() {
System.out.println("Apple");
}
}
class Orange implements fruit {
public void eat() {
System.out.println("Orange");
}
}
class Factory {
	public static fruit getInstance(String ClassName) {
		fruit f = null;
		try {
		f = (fruit) Class.forName(ClassName).newInstance();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return f;
	}
}
/**
* ������ͨ�Ĺ���ģʽ�����������һ�������ʱ�򣬾���Ҫ��Ӧ���޸Ĺ����ࡣ ��������Ӻܶ�������ʱ�򣬻���鷳��
* Java ����ģʽ���Բο�
* http://baike.xsoftlab.net/view/java-factory-pattern
* 
* �����������÷������ʵ�ֹ���ģʽ�������ڲ��޸Ĺ�����������������������ࡣ
* 
* ������һ����Ȼ���鷳��������Ҫ֪�������İ������������������ʹ��properties�����ļ�����ɡ�
* 
* java ��ȡ properties �����ļ� �ķ������Բο�
* http://baike.xsoftlab.net/view/java-read-the-properties-configuration-file
* 
* @author xsoftlab.net
*/
public class TestClass {
	
	public static void main(String[] args) throws Exception {
		fruit f = Factory.getInstance("com.zw.test.Orange");
		if (f != null) {
		f.eat();
		}
	}
}
