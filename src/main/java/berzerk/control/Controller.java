package berzerk.control;

import berzerk.control.state.ControllerState;
import berzerk.control.state.FactoryState;
import berzerk.model.Ecra;
import berzerk.model.Soldado;
import berzerk.model.menu.MenuModel;
import berzerk.view.menu.MenuView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Controller {
    ControllerState<?> state;
    Soldado soldado = new Soldado();
    Clip sound;

    private Clip loadSound() throws NullPointerException{
        try {
            String rootPath = new File(System.getProperty("user.dir")).getPath();
            File musicFile = new File(rootPath+"/src/main/resources/Royalty Free Nu Metal Instrumental - FRACTURES - DOWNLOAD.wav"); // Nu metal é um género de música Metal
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            Clip musicClip = AudioSystem.getClip();
            musicClip.open(audioInput);
            FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-25.0f);
            return musicClip;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startLoop() {
        sound.setMicrosecondPosition(0);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public Controller(FactoryState factory){
        state = factory.genMenuState(soldado, new MenuView(new MenuModel(), new Ecra()));
        starSound();
    }

    public void starSound(){
        sound = loadSound();
    }

    public void run() throws IOException, InterruptedException, URISyntaxException, FontFormatException {
        startLoop();
        do{
            state = state.run();
        }while (!isNull(state));
        sound.stop();
    }

    public boolean isNull(ControllerState<?> state){
        return state == null;
    }
}
