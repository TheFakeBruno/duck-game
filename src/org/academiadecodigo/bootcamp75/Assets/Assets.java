package Assets;

public class Assets {

    //Sets padding for the screen
    public static final int PADDING = 10;

    private final Screen screen;

    private final User user;

    public Assets(String screenImageLink, String userImageLink) {
        screen = new Screen(screenImageLink);
        user = new User(screen, userImageLink);
        screen.getScreen().draw();
        screen.getLowerRectangle().fill();
        user.getAvatar().draw();
    }

    public Screen getScreen() {
        return screen;
    }

    public User getUser() {
        return user;
    }

}


