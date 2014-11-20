package ua.cn.alexeenkogapon.arkanoid;

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Game {
	public static boolean init = false;// метка инициализации
	public static int cntBlocks = 0;// равно количеству убитых блоков
	public static int score = 0;//счет игрока
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
		/* количество блоков помещающихся на одной строке */
		int column = ball.getSCREEN_WIDTH() / (Block.WIDTH + 5);
		/*
		 * количество блоков помещающихся на одном столбце(только в верхней
		 * половине)
		 */
		int row = ball.getSCREEN_HEIGHT() / 2 / (Block.HEIGHT + 5);
		/* если блоков уже не осталось или мы еще их не проинициализировали */
		if ((cntBlocks == row * column) || (!init)) {
			/* если шар находится в нижней части экрана */
			if (ball.getPosy() > (ball.getSCREEN_HEIGHT() / 2 + ball.getSCREEN_HEIGHT() / 4)) {
				/* делаем равные отступы слева и справа */
				int tabh = (ball.getSCREEN_WIDTH() - (Block.WIDTH + 5) * column) / 2;
				/* делаем равные отступы сверху и снизу */
				int tabv = ((ball.getSCREEN_HEIGHT() / 2) - (Block.HEIGHT + 5)
						* row) / 2;
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < column; j++) {
						/* если все блоки уже убраны */
						if (cntBlocks == row * column) {
							/* очищаем список блоков */
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
																 * инициализируем
																 * блоки
																 */
					}
				}
			}
		}
		cntBlocks = 0;
		init = true;// инициализация проведена
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
