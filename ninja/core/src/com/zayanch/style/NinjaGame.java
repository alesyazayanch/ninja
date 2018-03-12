package com.zayanch.style;

import com.badlogic.gdx.Game;
import com.zayanch.style.screens.MainScreen;

public class NinjaGame extends Game {

	@Override
	public void create () {
		this.setScreen(new MainScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
