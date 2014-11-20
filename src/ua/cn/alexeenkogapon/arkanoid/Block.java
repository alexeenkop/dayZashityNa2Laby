package ua.cn.alexeenkogapon.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Block {
	private Point pos;
	private boolean dead;/* ��� �� ���� */
	public static final int WIDTH = 80;
	public static final int HEIGHT = 40;
	
	public Block(int x, int y, boolean dead) {
		super();
		this.pos = new Point(x, y);
		this.dead = dead;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(int x, int y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void draw(Canvas canvas) {
		if (!isDead()) {
			Paint p = new Paint();
			p.setColor(Color.RED);
			canvas.drawRect((float) pos.x, (float) pos.y, (float) (pos.x + WIDTH),
					(float) (pos.y + HEIGHT), p);
		}
	}

	/*
	 * �������� �� ������ ������ �� �����
	 */
	public void checkHit(Ball ball) {
		if (!isDead()) {// ���� ���� ��� �� ����
			double bpx = ball.getPosx();
			double bpy = ball.getPosy();
			// ����������� �� ������� �������
			if ((bpy + ball.getRadius() >= pos.y)
					&& (bpy + ball.getRadius() < pos.y + HEIGHT)
					&& (bpx >= pos.x)
					&& (bpx <= pos.x + WIDTH)) {
				ball.setVector(ball.getVx(), -ball.getVy());
				setDead(true);// �������� ���� ������
				ball.incrHits();// ����������� ������� ������
				Game.incScore();
			} else
			// ����������� �� ������ �������
			if ((bpy - ball.getRadius() > pos.y)
					&& (bpy - ball.getRadius() <= pos.y + HEIGHT)
					&& (bpx >= pos.x)
					&& (bpx <= pos.x + WIDTH)) {
				ball.setVector(ball.getVx(), -ball.getVy());
				setDead(true);// �������� ���� ������
				ball.incrHits();// ����������� ������� ������
				Game.incScore();
			} else
			// ����������� �� ����� �������
			if ((bpx + ball.getRadius() >= pos.x)
					&& (bpx + ball.getRadius() < pos.x + WIDTH)
					&& (bpy >= pos.y)
					&& (bpy <= pos.y + HEIGHT)) {
				ball.setVector(-ball.getVx(), ball.getVy());
				setDead(true);// �������� ���� ������
				ball.incrHits();// ����������� ������� ������
				Game.incScore();
			} else
			// ����������� �� ������ �������
			if ((bpx - ball.getRadius() > pos.x)
					&& (bpx - ball.getRadius() <= pos.x + WIDTH)
					&& (bpy >= pos.y)
					&& (bpy <= pos.y + HEIGHT)) {
				ball.setVector(-ball.getVx(), ball.getVy());
				setDead(true);// �������� ���� ������
				ball.incrHits();// ����������� ������� ������
				Game.incScore();
			}
	}
	}
}
