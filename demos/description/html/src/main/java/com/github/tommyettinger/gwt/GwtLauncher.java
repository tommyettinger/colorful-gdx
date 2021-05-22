package com.github.tommyettinger.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.github.tommyettinger.DescriptionDemo;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
		@Override
		public GwtApplicationConfiguration getConfig () {
			// Resizable application, uses available space in browser
//			return new GwtApplicationConfiguration(true);
			// Fixed size application:
			GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(DescriptionDemo.SCREEN_WIDTH, DescriptionDemo.SCREEN_HEIGHT);
			cfg.disableAudio = true;
			return cfg;
		}

		@Override
		public ApplicationListener createApplicationListener () { 
			return new DescriptionDemo();
		}
}
