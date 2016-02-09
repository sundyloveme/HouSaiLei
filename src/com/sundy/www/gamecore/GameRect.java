package com.sundy.www.gamecore;

import java.awt.Dimension;
import java.awt.Point;

public abstract class GameRect extends GameObject
{

	public abstract Dimension getSize();

	public abstract int getHeight();

	public abstract int getWidth();

	/**
	 * �жϾ�����rect�Ƿ��ཻ
	 * 
	 * @param rect
	 *            ��һ������
	 * @return true�� false���ཻ
	 */
	public Boolean xiangJiao(GameRect rect)
	{
		int a, b,h,ry;

		
		h=rect.getHeight();
		ry=rect.getY();
		a = rect.getY() + rect.getHeight();
		b = getY();
		// �Ͼ���
		if (rect.getY() + rect.getHeight() < getY())
		{
			return false;
		}

		a = rect.getY();
		b = getY() + getHeight();
		// �¾���
		if (rect.getY() > getY() + getHeight())
		{
			return false;
		}
		
		a = rect.getX() + rect.getWidth();
		b = getX();
		// �����
		if (rect.getX() + rect.getWidth() < getX())
		{
			return false;
		}
		
		a = getX() + getWidth();
		b = rect.getX();
		// �Ҿ���
		if (getX() + getWidth() < rect.getX())
		{
			return false;
		}

		return true;
	}

}
