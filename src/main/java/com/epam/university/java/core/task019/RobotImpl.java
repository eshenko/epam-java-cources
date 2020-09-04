package com.epam.university.java.core.task019;

public class RobotImpl implements Robot {
    private final RobotPosition startPosition = new RobotPositionImpl(0, 0);
    private RobotPosition currentPosition = new RobotPositionImpl(startPosition.getX(),
                                                                  startPosition.getY());
    private RobotDirection currentDirection = RobotDirection.UP;

    @Override
    public RobotPosition getPosition() {
        return currentPosition;
    }

    @Override
    public void setPosition(RobotPosition position) {
        currentPosition = position;
    }

    @Override
    public void invokeAction(RobotCommand command) {
        switch (command) {
            case MOVE_FORWARD:
                switch (currentDirection) {
                    case UP:
                        currentPosition.setY(currentPosition.getY() + 1);
                        break;
                    case DOWN:
                        currentPosition.setY(currentPosition.getY() - 1);
                        break;
                    case LEFT:
                        currentPosition.setX(currentPosition.getX() - 1);
                        break;
                    case RIGHT:
                        currentPosition.setX(currentPosition.getX() + 1);
                        break;
                    default:
                }
                break;
            case TURN_LEFT:
                switch (currentDirection) {
                    case UP:
                        currentDirection = RobotDirection.LEFT;
                        break;
                    case DOWN:
                        currentDirection = RobotDirection.RIGHT;
                        break;
                    case LEFT:
                        currentDirection = RobotDirection.DOWN;
                        break;
                    case RIGHT:
                        currentDirection = RobotDirection.UP;
                        break;
                    default:
                }
                break;
            case TURN_RIGHT:
                switch (currentDirection) {
                    case UP:
                        currentDirection = RobotDirection.RIGHT;
                        break;
                    case DOWN:
                        currentDirection = RobotDirection.LEFT;
                        break;
                    case LEFT:
                        currentDirection = RobotDirection.UP;
                        break;
                    case RIGHT:
                        currentDirection = RobotDirection.DOWN;
                        break;
                    default:
                }
                break;
            default:
        }
    }

    public RobotPosition getStartPosition() {
        return startPosition;
    }
}
