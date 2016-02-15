package com.uaq.captcha.overwrite;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.Random;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;

public class ChangedAttributedString {
	AttributedString[] aStrings;
	Rectangle2D[] bounds;
	LineMetrics[] metrics;
	private Random myRandom = new SecureRandom();
	private int kerning;

	protected ChangedAttributedString(Graphics2D g2, AttributedString aString, int kerning) {
		this.kerning = kerning;
		AttributedCharacterIterator iter = aString.getIterator();
		int n = iter.getEndIndex();
		this.aStrings = new AttributedString[n];
		this.bounds = new Rectangle2D[n];
		this.metrics = new LineMetrics[n];

		for (int i = iter.getBeginIndex(); i < iter.getEndIndex(); i++) {
			iter.setIndex(i);
			this.aStrings[i] = new AttributedString(iter, i, i + 1);
			Font font = (Font) iter.getAttribute(TextAttribute.FONT);
			if (font != null) {
				g2.setFont(font);
			}
			FontRenderContext frc = g2.getFontRenderContext();

			this.bounds[i] = g2.getFont().getStringBounds(iter, i, i + 1, frc);

			this.metrics[i] = g2.getFont().getLineMetrics(new Character(iter.current()).toString(), frc);
		}
	}

	void drawString(Graphics2D g2) {
		for (int i = 0; i < length(); i++)
			g2.drawString(getIterator(i), (float) getX(i), (float) getY(i));
	}

	void drawString(Graphics2D g2, ColorGenerator colorGenerator) {
		for (int i = 0; i < length(); i++) {
			g2.setColor(colorGenerator.getNextColor());
			g2.drawString(getIterator(i), (float) getX(i), (float) getY(i));
		}
	}

	Point2D moveToRandomSpot(BufferedImage background) {
		return moveToRandomSpot(background, null);
	}

	Point2D moveToRandomSpot(BufferedImage background, Point2D startingPoint) {
		int maxHeight = (int) getMaxHeight();

		@SuppressWarnings("unused")
		int arbitraryHorizontalPadding = 10;
		@SuppressWarnings("unused")
		int arbitraryVerticalPadding = 5;
		double maxX = background.getWidth() - getTotalWidth() - 10.0D;
		double maxY = background.getHeight() - maxHeight - 5;
		int newY;
		if (startingPoint == null) {
			newY = (int) getMaxAscent() + this.myRandom.nextInt(Math.max(1, (int) maxY));
		} else
			newY = (int) (startingPoint.getY() + this.myRandom.nextInt(10));

		if ((maxX < 0.0D) || (maxY < 0.0D)) {
			String problem = "too tall:";

			if ((maxX < 0.0D) && (maxY > 0.0D)) {
				problem = "too long:";

				useMinimumSpacing(this.kerning / 2);
				maxX = background.getWidth() - getTotalWidth();
				if (maxX < 0.0D) {
					useMinimumSpacing(0.0D);

					maxX = background.getWidth() - getTotalWidth();
					if (maxX < 0.0D) {
						maxX = reduceHorizontalSpacing(background.getWidth(), 0.05D);
					}

				}

				if (maxX > 0.0D) {
					moveTo(0.0D, newY);
					return new Point2D.Float(0.0F, newY);
				}

			}

			throw new CaptchaException("word is " + problem + " try to use less letters, smaller font" + " or bigger background: " + " text bounds = " + this + " with fonts " + getFontListing()
					+ " versus image width = " + background.getWidth() + ", height = " + background.getHeight());
		}
		int newX;
		if (startingPoint == null) {
			newX = this.myRandom.nextInt(Math.max(1, (int) maxX));
		} else
			newX = (int) (startingPoint.getX() + this.myRandom.nextInt(10));

		moveTo(newX, newY);
		return new Point2D.Float(newX, newY);
	}

	String getFontListing() {
		StringBuffer buf = new StringBuffer();
		@SuppressWarnings("unused")
		String RS = "\n\t";
		buf.append("{");
		for (int i = 0; i < length(); i++) {
			AttributedCharacterIterator iter = this.aStrings[i].getIterator();
			Font font = (Font) iter.getAttribute(TextAttribute.FONT);
			if (font != null) {
				buf.append(font.toString()).append("\n\t");
			}
		}
		buf.append("}");
		return buf.toString();
	}

	void useMonospacing(double kerning) {
		double maxWidth = getMaxWidth();

		for (int i = 1; i < this.bounds.length; i++) {
			getBounds(i).setRect(getX(i - 1) + maxWidth + kerning, getY(i), getWidth(i), getHeight(i));
		}
	}

	void useMinimumSpacing(double kerning) {
		for (int i = 1; i < length(); i++)
			this.bounds[i].setRect(this.bounds[(i - 1)].getX() + this.bounds[(i - 1)].getWidth() + kerning, this.bounds[i].getY(), this.bounds[i].getWidth(), this.bounds[i].getHeight());
	}

	double reduceHorizontalSpacing(int imageWidth, double maxReductionPct) {
		double maxX = imageWidth - getTotalWidth();

		double pct = 0.0D;
		double stepSize = maxReductionPct / 25.0D;
		for (pct = stepSize; (pct < maxReductionPct) && (maxX < 0.0D); pct += stepSize) {
			for (int i = 1; i < length(); i++) {
				this.bounds[i].setRect((1.0D - pct) * this.bounds[i].getX(), this.bounds[i].getY(), this.bounds[i].getWidth(), this.bounds[i].getHeight());
			}

			maxX = imageWidth - getTotalWidth();
		}
		return maxX;
	}

	void moveTo(double newX, double newY) {
		this.bounds[0].setRect(newX, newY, this.bounds[0].getWidth(), this.bounds[0].getHeight());
		for (int i = 1; i < length(); i++)
			this.bounds[i].setRect(newX + this.bounds[i].getX(), newY, this.bounds[i].getWidth(), this.bounds[i].getHeight());
	}

	protected void shiftBoundariesToNonLinearLayout(double backgroundWidth, double backgroundHeight) {
		double newX = backgroundWidth / 20.0D;
		double middleY = backgroundHeight / 2.0D;
		Random myRandom = new SecureRandom();

		this.bounds[0].setRect(newX, middleY, this.bounds[0].getWidth(), this.bounds[0].getHeight());
		for (int i = 1; i < length(); i++) {
			double characterHeight = this.bounds[i].getHeight();
			double randomY = myRandom.nextInt() % (backgroundHeight / 4.0D);
			double currentY = middleY + (myRandom.nextBoolean() ? randomY : -randomY) + characterHeight / 4.0D;
			this.bounds[i].setRect(newX + this.bounds[i].getX(), currentY, this.bounds[i].getWidth(), this.bounds[i].getHeight());
		}
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{text=");
		for (int i = 0; i < length(); i++) {
			buf.append(this.aStrings[i].getIterator().current());
		}
		@SuppressWarnings("unused")
		String RS = "\n\t";
		buf.append("\n\t");
		for (int i = 0; i < length(); i++) {
			buf.append(this.bounds[i].toString());
			@SuppressWarnings("unused")
			String FS = " ";
			LineMetrics m = this.metrics[i];

			buf.append(" ascent=").append(m.getAscent()).append(" ");
			buf.append("descent=").append(m.getDescent()).append(" ");
			buf.append("leading=").append(m.getLeading()).append(" ");

			buf.append("\n\t");
		}
		buf.append("}");
		return buf.toString();
	}

	public int length() {
		return this.bounds.length;
	}

	public double getX(int index) {
		return getBounds(index).getX();
	}

	public double getY(int index) {
		return getBounds(index).getY();
	}

	public double getHeight(int index) {
		return getBounds(index).getHeight();
	}

	public double getTotalWidth() {
		return getX(length() - 1) + getWidth(length() - 1);
	}

	public double getWidth(int index) {
		return getBounds(index).getWidth();
	}

	public double getAscent(int index) {
		return getMetric(index).getAscent();
	}

	double getDescent(int index) {
		return getMetric(index).getDescent();
	}

	public double getMaxWidth() {
		double maxWidth = -1.0D;

		for (int i = 0; i < this.bounds.length; i++) {
			double w = getWidth(i);

			if (maxWidth < w) {
				maxWidth = w;
			}
		}
		return maxWidth;
	}

	public double getMaxAscent() {
		double maxAscent = -1.0D;

		for (int i = 0; i < this.bounds.length; i++) {
			double a = getAscent(i);

			if (maxAscent < a) {
				maxAscent = a;
			}
		}
		return maxAscent;
	}

	public double getMaxDescent() {
		double maxDescent = -1.0D;

		for (int i = 0; i < this.bounds.length; i++) {
			double d = getDescent(i);

			if (maxDescent < d) {
				maxDescent = d;
			}
		}
		return maxDescent;
	}

	public double getMaxHeight() {
		double maxHeight = -1.0D;
		for (int i = 0; i < this.bounds.length; i++) {
			double h = getHeight(i);

			if (maxHeight < h) {
				maxHeight = h;
			}
		}
		return maxHeight;
	}

	public double getMaxX() {
		return getX(0) + getTotalWidth();
	}

	public double getMaxY() {
		return getY(0) + getMaxHeight();
	}

	public Rectangle2D getBounds(int index) {
		return this.bounds[index];
	}

	public LineMetrics getMetric(int index) {
		return this.metrics[index];
	}

	public AttributedCharacterIterator getIterator(int i) {
		return this.aStrings[i].getIterator();
	}
}