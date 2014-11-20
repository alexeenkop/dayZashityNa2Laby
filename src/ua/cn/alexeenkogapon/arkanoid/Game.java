package ua.cn.alexeenkogapon.arkanoid;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Game {
	public static boolean init = false;// ����� �������������
	public static int cntBlocks = 0;// ����� ���������� ������ ������
	public static int score = 0;//���� ������
	public static boolean isInit() {
		return init;
	}

	public static void setInit(boolean init) {
		Game.init = init;
	}

	public static int getCntBlocks() {
		return cntBlocks;
	}

	public static void setCntBlocks(int cntBlocks) {
		Game.cntBlocks = cntBlocks;
	}
	public static void incScore() {
		score += 1;
	}
	public static void resetScore() {
		score = 0;
	}
	public static void initBlocks(List<Block> block, Ball ball) {
		/* ���������� ������ ������������ �� ����� ������ */
		int column = ball.getSCREEN_WIDTH() / (Block.WIDTH + 5);
		/*
		 * ���������� ������ ������������ �� ����� �������(������ � �������
		 * ��������)
		 */
		int row = ball.getSCREEN_HEIGHT() / 2 / (Block.HEIGHT + 5);
		/* ���� ������ ��� �� �������� ��� �� ��� �� �� ������������������� */
		if ((cntBlocks == row * column) || (!init)) {
			/* ���� ��� ��������� � ������ ����� ������ */
			if (ball.getPosy() > (ball.getSCREEN_HEIGHT() / 2 + ball.getSCREEN_HEIGHT() / 4)) {
				/* ������ ������ ������� ����� � ������ */
				int tabh = (ball.getSCREEN_WIDTH() - (Block.WIDTH + 5) * column) / 2;
				/* ������ ������ ������� ������ � ����� */
				int tabv = ((ball.getSCREEN_HEIGHT() / 2) - (Block.HEIGHT + 5)
						* row) / 2;
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						/* ���� ��� ����� ��� ������ */
						if (cntBlocks == row * column) {
							/* ������� ������ ������ */
							block.removeAll(block);
							cntBlocks = 0;
							ball.setHits(0);
							if (ball.getLife() == 0) {
								ball.resetSpeed();
								ball.setLife(3);
							}
						}
						block.add(new Block(tabh + j * (Block.WIDTH + 5), tabv
								+ i * (Block.HEIGHT + 5), false));/*
																 * ��������������
																 * �����
																 */
					}
				}
			}
		}
		cntBlocks = 0;
		init = true;// ������������� ���������
	}

	public void drawStatistic(Canvas canvas, Ball ball) {
		Paint mScorePaint = new Paint();
		mScorePaint.setTextSize(20);
		mScorePaint.setStrokeWidth(3);
		mScorePaint.setStyle(Style.FILL);
		mScorePaint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("Lives left: " + ball.getLife(),
				(float) canvas.getWidth() / 2,
				(float) canvas.getHeight() / 2 + 200, mScorePaint);
		canvas.drawText("Hits: " + ball.getHits(),
				(float) canvas.getWidth() / 2,
				(float) canvas.getHeight() / 2 + 220, mScorePaint);
		canvas.drawText("Speed: " + ball.getSpeed(),
				(float) canvas.getWidth() / 2,
				(float) canvas.getHeight() / 2 + 240, mScorePaint);
		canvas.drawText("Score: " + this.score,
				(float) canvas.getWidth() / 2,
				(float) canvas.getHeight() / 2 + 260, mScorePaint);
	}
}
