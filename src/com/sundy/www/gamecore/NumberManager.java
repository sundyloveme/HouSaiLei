package com.sundy.www.gamecore;

//����ģʽ
public class NumberManager
{
	private int i;
	// ����һ����������
	public static NumberManager instance = new NumberManager();

	/**
	 * ���һ���µı��
	 * 
	 * @return ���
	 */
	public int getNum()
	{
		return i++;
	}

	// ˽�еĹ��캯��
	private NumberManager()
	{
	}
}
