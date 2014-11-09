package ua.cn.alexeenkogapon.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Block {
	private Point pos;
	private boolean dead;/* был ли удар */
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
	 * проверка на отскок шарика от блока
	 */
	public void checkHit(Ball ball) {
		if (!isDead()) {// если блок еще не убит
			Point bp = ball.getPos();
			// отскакиваем от верхней границы
			if ((bp.y + ball.getRadius() >= pos.y)
					&& (bp.y + ball.getRadius() < pos.y + HEIGHT)
					&& (bp.x >= pos.x)
					&& (bp.x <= pos.x + WIDTH)) {
				ball.setVector(ball.getVx(), -ball.getVy());
				setDead(true);// помечаем блок убитым
				ball.incrHits();// увеличиваем счетчик убитых
				Game.incScore();
			} else
			// отскакиваем от нижней границы
			if ((bp.y - ball.getRadius() > pos.y)
					&& (bp.y - ball.getRadius() <= pos.y + HEIGHT)
					&& (bp.x >= pos.x)
					&& (bp.x <= pos.x + WIDTH)) {
				ball.setVector(ball.getVx(), -ball.getVy());
				setDead(true);// помечаем блок убитым
				ball.incrHits();// увеличиваем счетчик убитых
				Game.incScore();
			} else
			// отскакиваем от левой границы
			if ((bp.x + ball.getRadius() >= pos.x)
					&& (bp.x + ball.getRadius() < pos.x + WIDTH)
					&& (bp.y >= pos.y)
					&& (bp.y <= pos.y + HEIGHT)) {
				ball.setVector(-ball.getVx(), ball.getVy());
				setDead(true);// помечаем блок убитым
				ball.incrHits();// увеличиваем счетчик убитых
				Game.incScore();
			} else
			// отскакиваем от правой границы
			if ((bp.x - ball.getRadius() > pos.x)
					&& (bp.x - ball.getRadius() <= pos.x + WIDTH)
					&& (bp.y >= pos.y)
					&& (bp.y <= pos.y + HEIGHT)) {
				ball.setVector(-ball.getVx(), ball.getVy());
				setDead(true);// помечаем блок убитым
				ball.incrHits();// увеличиваем счетчик убитых
				Game.incScore();
			}
	}
	}
}
