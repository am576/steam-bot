package com.steam;

/**
 * Friend
 * <p>
 * An object representing an entry in a friends list.
 *
 * @author Ian
 * @version 1.0
 */
public class Friend {
    private final long userId;
    private final Relationship relationship;
    private final long relationshipDate;

    /**
     * Creates a new friend with the specified characteristics.
     *
     * @param userId           the user identifier for the friend
     * @param relationship     the current relationship status
     * @param relationshipDate the date in which the friendship began
     */
    public Friend(long userId, Relationship relationship, long relationshipDate) {
        this.userId = userId;
        this.relationship = relationship;
        this.relationshipDate = relationshipDate;
    }

    /**
     * The user identifier of the friend.
     *
     * @return the user identifier
     */
    public long getUserId() {
        return userId;
    }

    /**
     * The current relationship status of the friend.
     *
     * @return the current friend relationship status
     */
    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * The time in which the friendship began.
     *
     * @return the time in which friendship began
     */
    public long getRelationshipDate() {
        return relationshipDate;
    }
}
