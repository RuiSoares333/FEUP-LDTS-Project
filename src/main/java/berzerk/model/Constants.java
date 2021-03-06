package berzerk.model;

import com.googlecode.lanterna.TextColor;

public final class Constants {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 40;
    public static final int MAX_NOME_JOGADOR = 3;


    public static final String MENU_BACKGROUND_COLOR = "#270F41";
    public static final String MENU_LETTER_COLOR = "#F6C370";

    public static final String GAME_BACKGROUND_COLOR = "#000000";
    public static final String GAME_WALL_COLOR = "#5C62F7";

    public static final String GAME_BULLET_COLOR = "#FFFFFF";

    public static final String RECRUIT_COLOR = "#558B2F";
    public static final String TANKY_COLOR = "#781212";
    public static final String EXPERT_COLOR = "#616161";

    public static final String MONSTER_COLOR = "#24A120";
    public static final String DEMENTOR_COLOR = "#CC9900";

    public static TextColor getHeroColor(String heroType){
        if(heroType!=null) {
            return switch (heroType) {
                case "Recruit" -> TextColor.Factory.fromString(Constants.RECRUIT_COLOR);
                case "Tanky" -> TextColor.Factory.fromString(Constants.TANKY_COLOR);
                case "Expert" -> TextColor.Factory.fromString(Constants.EXPERT_COLOR);
                default -> TextColor.Factory.fromString("#FFFF33");
            };
        }
        return TextColor.Factory.fromString("#FFFF33");
    }


    public static final String[] GAME_NAME= {
            ",---.    ____   _____ ______  ____   _____  _   _",
            "| |) |  | ___| |  _ | ----// | ___| |  _ | | | //",
            "|    /  ||_    | | )|    //  ||_    | | )| | |//",
            "|  _  \\ | _|   |  - /   //   | _|   |  - / | //",
            "| | ) | ||___  | |\\\\   //    ||___  | |\\\\  | |\\\\",
            "|  -  / |____| | || | //---- |____| | || | | || |",
            "|____/         |_||_| ------        |_||_| |_||_|"
    };

    public static final String[] RECRUTA_SIM= {
            "     pppppp     ",
            "   ppggggggpp   ",
            "  ppgggggggvpp  ",
            "  pgggggggvvvp  ",
            " ppggvvvvvvvvpp ",
            "pVVvvvvvvvvvVVVp",
            "ppVVVVVVVVVVVVpp",
            " pppppppppppppp ",
            "  chhhhhhhhhhc  ",
            "  chhbbhhbbhhc  ",
            "  chhhhhhhhhhc  ",
            "   cssssssssc   ",
            "   cssssssssc   ",
            "    cssppssc    ",
            "    cssssssc    ",
            "     cccccc     "
    };

    public static final String[] TANKY_SIM= {
            "     pppppp     ",
            "    pvvvvvvp    ",
            "   pvvvvvvvvp   ",
            "  pvvvvvvvvvp   ",
            "  pppppvvvvvppp ",
            " pacaAApvvvvpvvp",
            " pAAAAApvvvVpvvp",
            " pAAAAApvvvVpVvp",
            "  pppppvvvVVpVVp",
            "  pVvvvvvvVVpVVp",
            "  pVvvvvvVVVppp ",
            "  pVVVVVVVVVp   ",
            "  pVVVVpVVVVp   ",
            "  pVVVp pVVVp   ",
            "  pVVVp pVVVp   ",
            "   ppp   ppp    "
    };

    public static final String[] EXPERT_SIM= {
            "    pppppppp    ",
            "  ppccccccccpp  ",
            "  pppppppppppp  ",
            " pccccccccccccp ",
            "pcppppppppppppcp",
            "pcpvrrvvvvrrvpcp",
            " pvlrllllllrlvp ",
            " pvlrllllllrlvp ",
            " pvllllllllllvp ",
            "pcpvlvppppvlvpcp",
            "pccpppccccpppccp",
            " pcccpcppcpcccp ",
            "  ppcpccccpcpp  ",
            "   pcpccccpcp   ",
            "    ppccccpp    ",
            "      pppp      "
    };


    public static final String[] TROPHY={
            "  oooommmmammm  ",
            "   oooooooooo   ",
            "ammmooommmammmaa",
            "a  oooommmamm  a",
            "a  oooommmamm  m",
            "m  oooommmamm  m",
            "m  oooommmamm  m",
            "mmmmooommmammmmm",
            "   oooommmamm   ",
            "   oooommmmmm   ",
            "    oooooooo    ",
            "      oooo      ",
            "       oa       ",
            "      ooma      ",
            "    ooommaam    ",
            "  oooommmmammm  "
    };

}
