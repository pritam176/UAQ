package com.uaq.captcha.overwrite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

import com.octo.captcha.CaptchaException;
import com.octo.captcha.component.image.color.ColorGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.textpaster.SimpleTextPaster;

public class CustomSimpleTextPaster extends SimpleTextPaster {

	private ColorGenerator colorGenerator = new SingleColorGenerator(Color.white);

	public CustomSimpleTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, Color textColor) {
		super(minAcceptedWordLength, maxAcceptedWordLength, textColor);
	}

	public CustomSimpleTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator) {
		super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator);
	}

	public CustomSimpleTextPaster(Integer minAcceptedWordLength, Integer maxAcceptedWordLength, ColorGenerator colorGenerator, Boolean manageColorPerGlyph) {
		super(minAcceptedWordLength, maxAcceptedWordLength, colorGenerator, manageColorPerGlyph);
	}

	public BufferedImage pasteText(BufferedImage background, AttributedString attributedWord) throws CaptchaException {
		int x = (background.getWidth() / 20) + 5;
		int y = background.getHeight() / 2 + 10;
		BufferedImage out = copyBackgroundOverwritten(background);
		Graphics2D g2 = pasteBackgroundAndSetTextColorUpdated(out, background);

		ChangedAttributedString newAttrString = new ChangedAttributedString(g2, attributedWord, 2);

		newAttrString.useMinimumSpacing(1.0D);

		newAttrString.moveTo(x, y);

		if (isManageColorPerGlyph())
			newAttrString.drawString(g2, getColorGenerator());
		else {
			newAttrString.drawString(g2);
		}
		g2.dispose();
		return out;
	}

	private BufferedImage copyBackgroundOverwritten(BufferedImage background) {
		BufferedImage out = new BufferedImage(background.getWidth(), background.getHeight(), background.getType());

		return out;
	}

	private Graphics2D pasteBackgroundAndSetTextColorUpdated(BufferedImage out, BufferedImage background) {
		Graphics2D pie = (Graphics2D) out.getGraphics();

		pie.drawImage(background, 0, 0, out.getWidth(), out.getHeight(), null);

		pie.setColor(this.colorGenerator.getNextColor());
		return pie;
	}
}