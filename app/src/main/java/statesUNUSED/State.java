package statesUNUSED;

/**
 * Created by 343076 on 31/01/2016.
 */
public abstract class State{

    private StateManager sm;

    public State(StateManager sm){
        this.sm = sm;
    }

    protected abstract void handleInput();
    protected abstract void update();
    public abstract void dispose();
}
