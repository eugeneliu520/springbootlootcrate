package com.eugene.springboot.lootcrate.event;

import org.springframework.core.ResolvableType;

/**
 * @author eugeneliu
 * @ClassName LootCrateEvent
 * @Description TODO
 * @Date 2024/4/30 4:14 PM
 **/
public class LootCrateEvent implements java.io.Serializable{
    protected Object source;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public LootCrateEvent(Object source) {
        if (source == null) {
            throw new IllegalArgumentException("null source");
        }

        this.source = source;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return the object on which the Event initially occurred
     */
    public Object getSource() {
        return source;
    }

    /**
     * Returns a String representation of this EventObject.
     *
     * @return a String representation of this EventObject
     */
    @Override
    public String toString() {
        return getClass().getName() + "[source=" + source + "]";
    }
}
