package org.github.dustinbarnes.stack;


public class ConstantTimeStack
{
    private int[] dataStack;
    int dataPosition = 0;

    private int[] minStack;
    int minPosition = 0;

    public ConstantTimeStack(int capacity)
    {
        dataStack = new int[capacity];
        minStack = new int[capacity];
    }

    public void push(int value)
    {
        dataStack[dataPosition] = value;
        dataPosition++;

        if ( minPosition == 0 || value <= min() )
        {
            minStack[minPosition] = value;
            minPosition++;
        }
    }

    public int pop()
    {
        int value = dataStack[dataPosition - 1];
        dataPosition--;

        if ( value == min() )
        {
            minPosition--;
        }

        return value;
    }

    public int min()
    {
        int value = -1;

        if ( dataPosition > 0 )
        {
            value = minStack[minPosition - 1];
        }

        return value;
    }

    // Not part of the spec, but helps with testing.
    public int size()
    {
        return dataPosition;
    }
}
