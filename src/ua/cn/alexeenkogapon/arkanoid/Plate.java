package ua.cn.alexeenkogapon.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

public class Plate {
	private Point pos;
	public static final int WIDTH = 80;
	public static final int HEIGHT = 20;

	public Plate(Ball ball) {
		super();
		this.pos = new Point((ball.getSCREEN_WIDTH() - WIDTH) / 2, ball.getSCREEN_HEIGHT() - HEIGHT);
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(int x, int y) {
		this.pos.x = x;
		this.pos.y = y;
	}

	public void draw(Canvas canvas) {
		Paint p = new Paint();
		p.setColor(Color.GRAY);
		canvas.drawRect((float) pos.x, (float) pos.y, (float) (pos.x + WIDTH),
				(float) (pos.y + HEIGHT + 40), p);
	}

	public void move(int x, int width) {
		if ((x >= 40) && (x <= width - 40)) {
			this.pos.x = x - 40;
		}else
		if (x < 40) {
			this.pos.x = 0;
		}else
		if (x > width - 40) {
			this.pos.x = width - 80;
		}
		
	}

	public void checkHit(Ball ball) {
		Point bp = ball.getPos();
		// отскакиваем от верхней границы
		if ((bp.y + ball.getRadius() >= pos.y)
				&& (bp.y + ball.getRadius() < pos.y + HEIGHT)
				&& (bp.x >= pos.x) && (bp.x <= pos.x + WIDTH)) {
			ball.setVector(ball.getVx(), -ball.getVy());
		} else
		// отскакиваем от нижней границы
		if ((bp.y - ball.getRadius() > pos.y)
				&& (bp.y - ball.getRadius() <= pos.y + HEIGHT)
				&& (bp.x >= pos.x) && (bp.x <= pos.x + WIDTH)) {
			ball.setVector(ball.getVx(), -ball.getVy());
		} else
		// отскакиваем от левой границы
		if ((bp.x + ball.getRadius() >= pos.x)
				&& (bp.x + ball.getRadius() < pos.x + WIDTH)
				&& (bp.y >= pos.y) && (bp.y <= pos.y + HEIGHT)) {
			ball.setVector(-ball.getVx(), ball.getVy());
		} else
		// отскакиваем от правой границы
		if ((bp.x - ball.getRadius() > pos.x)
				&& (bp.x - ball.getRadius() <= pos.x + WIDTH)
				&& (bp.y >= pos.y) && (bp.y <= pos.y + HEIGHT)) {
			ball.setVector(-ball.getVx(), ball.getVy());
		}
	}
	
}
