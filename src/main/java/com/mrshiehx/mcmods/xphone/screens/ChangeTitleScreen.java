package com.mrshiehx.mcmods.xphone.screens;

import com.mrshiehx.mcmods.xphone.modules.TitleManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.TranslatableText;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ChangeTitleScreen extends GameOptionsScreen {
	private TextFieldWidget textField;

	public ChangeTitleScreen(Screen parent, GameOptions gameOptions) {
		super(parent, gameOptions, new TranslatableText("options.changeTitle.title"));
	}

	protected void init() {
		this.textField = new TextFieldWidget(this.textRenderer, this.width / 2 - 100, this.height / 6 + 24, 200, 20, this.textField, new TranslatableText("changeTitle.change"));

		textField.setMaxLength(64);
		textField.setText(TitleManager.windowTitle);
		addButton(textField);
		this.addButton(new ButtonWidget(this.width / 2 - 100, this.height / 6 + 24 * (7 >> 1), 200, 20, ScreenTexts.YES, (button) -> {
			if(!Objects.equals(TitleManager.windowTitle,textField.getText()))TitleManager.setTitle(textField.getText());
			if(client!=null)this.client.openScreen(this.parent);
		}));
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 20, 16777215);
		super.render(matrices, mouseX, mouseY, delta);
	}
}
