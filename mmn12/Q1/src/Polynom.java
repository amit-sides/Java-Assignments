import javafx.util.Pair;

import java.util.*;
import java.util.function.DoubleBinaryOperator;

public class Polynom {
    private ArrayList<Pair<Integer, Double>> terms;

    public Polynom(int[] powers, double[] coefficients) throws Exception
    {
        // Validate input
        if (powers.length != coefficients.length)
        {
            throw new Exception("Invalid arrays lengths");
        }

        // Build hashmap that converts power to it's coefficient (solves multiple occurrences of the same power problem)
        HashMap<Integer, Double> powerToCoefficient = new HashMap<>();
        for (int i = 0; i < powers.length; i++)
        {
            // Skip zero coefficients
            if (coefficients[i] == 0)
            {
                continue;
            }

            // Add the coefficient to the sum of coefficients of this power
            powerToCoefficient.put(powers[i], powerToCoefficient.getOrDefault(powers[i], 0.0) + coefficients[i]);
            if (powerToCoefficient.get(powers[i]) == 0)
            {
                powerToCoefficient.remove(powers[i]);
            }
        }

        // Create the ArrayList
        createTermsArrayUsingHashMap(powerToCoefficient);
    }

    public Polynom(Polynom other)
    {
        terms = new ArrayList<>();
        for (Pair<Integer, Double> term : other.terms)
        {
            terms.add(new Pair<>(term.getKey(), term.getValue()));
        }
    }

    public Polynom()
    {
        // Create the zero polynom y=0
        terms = new ArrayList<>();
    }

    private void createTermsArrayUsingHashMap(HashMap<Integer, Double> powerToCoefficient)
    {
        // Build terms array
        terms = new ArrayList<>();
        for (Integer power : powerToCoefficient.keySet())
        {
            terms.add(new Pair<>(power, powerToCoefficient.get(power)));
        }

        // Sort terms array
        terms.sort((term1, term2) -> term2.getKey() - term1.getKey());
    }

    private HashMap<Integer, Double> combineTwoPolynomsUsingAction(Polynom other, DoubleBinaryOperator action)
    {
        // Build hashmap that converts power to it's coefficient
        HashMap<Integer, Double> powerToCoefficient = new HashMap<>();

        // Add the terms of this polynom
        for (Pair<Integer, Double> term : terms)
        {
            powerToCoefficient.put(term.getKey(), term.getValue());
        }

        // Execute the given action (plus / minus) using the coefficients of the two polynoms (or zero if it doesn't exist)
        for (Pair<Integer, Double> term : other.terms)
        {
            double newCoefficient = action.applyAsDouble(powerToCoefficient.getOrDefault(term.getKey(), 0.0), term.getValue());
            if (newCoefficient == 0)
            {
                powerToCoefficient.remove(term.getKey());
            }
            else
            {
                powerToCoefficient.put(term.getKey(), newCoefficient);
            }
        }

        return powerToCoefficient;
    }

    public Polynom plus(Polynom other)
    {
        // Create the hash map for the two polynoms with the plus action
        HashMap<Integer, Double> powerToCoefficient = combineTwoPolynomsUsingAction(other, (coef1, coef2) -> coef1 + coef2);

        // Create and returns the result polynom
        Polynom result = new Polynom();
        result.createTermsArrayUsingHashMap(powerToCoefficient);
        return result;
    }

    public Polynom minus(Polynom other)
    {
        // Create the hash map for the two polynoms with the minus action
        HashMap<Integer, Double> powerToCoefficient = combineTwoPolynomsUsingAction(other, (coef1, coef2) -> coef1 - coef2);

        // Create and return the result polynom
        Polynom result = new Polynom();
        result.createTermsArrayUsingHashMap(powerToCoefficient);
        return result;
    }

    public Polynom derivative()
    {
        Polynom derivativePolynom = new Polynom();

        // Perform the derivative
        for (Pair<Integer, Double> term : terms)
        {
            if (term.getKey() * term.getValue() == 0)
            {
                continue;
            }
            derivativePolynom.terms.add(new Pair<>(term.getKey() - 1, term.getKey() * term.getValue()));
        }

        return derivativePolynom;
    }

    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        if (terms.size() <= 0)
        {
            output.append("+0");
        }

        // Handle all terms
        for (Pair<Integer, Double> term : terms)
        {
            // Add sign and coefficient
            if (term.getValue() >= 0)
            {
                output.append("+"); // Add + sign between terms
            }
            if (term.getValue() == -1)
            {
                output.append("-"); // Handle special case of -1 as coefficient (should be -x, not -1x)
            }
            else if (term.getValue() != 1) // Handle special case of 1 as coefficient (should be x, not 1x)
            {
                output.append(term.getValue());
            }
            else if (term.getKey() == 0) // Handle special case where 1 is coefficient with power of zero
            {
                output.append("1");
            }

            // Add power
            if (term.getKey() != 0)
            {
                if (term.getKey() == 1)
                {
                    output.append("x");
                    continue;
                }
                output.append("x^");
                output.append(term.getKey());
            }
        }

        // Remove sign of first coefficient if it is positive
        if (terms.size() > 0 && terms.get(0).getValue() < 0)
        {
            return output.toString();
        }
        return output.substring(1);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }
        // Check if object is an instance of Polynom
        if (!(o instanceof Polynom))
        {
            return false;
        }
        Polynom other = (Polynom)o;

        // Check number of terms
        if (terms.size() != other.terms.size())
        {
            return false;
        }

        // Compare powers and coefficients (since the powers are sorted, it's easy)
        for (int i = 0; i < terms.size(); i++)
        {
            // Compares power
            if (!terms.get(i).getKey().equals(other.terms.get(i).getKey()))
            {
                return false;
            }

            // Compare coefficient
            if (!terms.get(i).getValue().equals(other.terms.get(i).getValue()))
            {
                return false;
            }
        }

        return true;
    }
}
