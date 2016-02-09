package com.sundy.www.gamecore;

import java.awt.Color;
import java.awt.Point;

import com.rupeng.game.GameCore;

public class GameText extends GameObject
{
	// �ı��ı��
	private int textNum;

	/**
	 * ���캯��
	 * 
	 * @param text
	 *            �ı�����
	 */
	public GameText(String text)
	{
		this.textNum = NumberManager.instance.getNum();
		GameCore.createText(this.textNum, text);
	}

	/**
	 * ȱʡ���캯��
	 */
	public GameText()
	{
		this("");
	}

	/**
	 * ��ʾ�ı�
	 */
	@Override
	public void show()
	{
		GameCore.showText(textNum);
	}

	/**
	 * �����ı�
	 */
	@Override
	public void hide()
	{
		GameCore.hideText(textNum);
	}

	/**
	 * �������
	 */
	@Override
	public Point getPosition()
	{
		return GameCore.getTextPosition(textNum);
	}

	/**
	 * ��������
	 */
	@Override
	public void setPosition(Point pos)
	{
		GameCore.setTextPosition(textNum, pos.x, pos.y);
	}

	/**
	 * ��������
	 */
	@Override
	public void setPosition(int x, int y)
	{
		GameCore.setTextPosition(textNum, x, y);
	}

	/**
	 * ���x
	 */
	@Override
	public int getX()
	{
		return GameCore.getTextX(textNum);
	}

	/**
	 * ���y
	 */
	@Override
	public int getY()
	{
		return GameCore.getTextY(textNum);
	}
	
	/**
	 * ��������
	 * @param text
	 */
	public void setText(String text)
	{
		GameCore.setText(this.textNum, text);
	}

	/**
	 * ������ɫ
	 * @param color
	 */
	public void setTextColor(Color color)
	{
		GameCore.setTextColor(this.textNum, color);
	}
	
	/**
	 * �����ı���С
	 * @param size
	 */
	public void setTextFontSize(int size)
	{
		GameCore.setTextFontSize(this.textNum, size);
	}
	
	/**
	 * �Ƴ�����
	 */
	public void remove()
	{
		GameCore.removeText(textNum);
	}
}
