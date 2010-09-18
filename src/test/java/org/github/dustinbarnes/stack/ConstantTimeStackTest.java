package org.github.dustinbarnes.stack;

import java.util.Random;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class ConstantTimeStackTest
{
    private static final int NUM_ELEMENTS = 1000;

    @Test
    public void testStackInNaturalOrder()
    {
        ConstantTimeStack stack = new ConstantTimeStack(NUM_ELEMENTS);
        for ( int i = 0; i < NUM_ELEMENTS; i++ )
        {
            stack.push(i);
            assertThat(stack.min(), equalTo(0));
            assertThat(stack.size(), equalTo(i+1));
        }

        // pop all but one
        for ( int i = 0; i < NUM_ELEMENTS-1; i++ )
        {
            stack.pop();
            assertThat(stack.min(), equalTo(0));
            assertThat(stack.size(), equalTo(NUM_ELEMENTS - 1 - i));
        }

        assertThat(stack.min(), equalTo(0));
        assertThat(stack.size(), equalTo(1));
    }

    @Test
    public void testStackInReverseNaturalOrder()
    {
        ConstantTimeStack stack = new ConstantTimeStack(NUM_ELEMENTS);
        for ( int i = 0; i < NUM_ELEMENTS; i++ )
        {
            int value = NUM_ELEMENTS - i;
            stack.push(value);
            assertThat(stack.min(), equalTo(value));
            assertThat(stack.size(), equalTo(i+1));
        }

        // pop all but one
        for ( int i = 0; i < NUM_ELEMENTS-1; i++ )
        {
            assertThat(stack.min(), equalTo(i+1));
            stack.pop();
            assertThat(stack.size(), equalTo(NUM_ELEMENTS - 1 - i));
        }

        assertThat(stack.min(), equalTo(NUM_ELEMENTS));
        assertThat(stack.size(), equalTo(1));
    }

    @Test
    public void testStackInRandomOrder()
    {
        ConstantTimeStack stack = new ConstantTimeStack(NUM_ELEMENTS);

        int localMin = Integer.MIN_VALUE;
        int firstNumber = 0;
        Random generator = new Random();

        for ( int i = 0; i < NUM_ELEMENTS; i++ )
        {
            int value = generator.nextInt();
            if ( i == 0 )
            {
                firstNumber = value;
                localMin = value;
            }
            localMin = Math.min(localMin, value);
            stack.push(value);
            assertThat(stack.min(), equalTo(localMin));
            assertThat(stack.size(), equalTo(i+1));
        }

        // pop all but one
        for ( int i = 0; i < NUM_ELEMENTS-1; i++ )
        {
            stack.pop();
            assertThat(stack.size(), equalTo(NUM_ELEMENTS - 1 - i));
        }

        assertThat(stack.min(), equalTo(firstNumber));
    }
}
