# chess.checker
[![Build Status](https://travis-ci.com/mudkipmaster/chess.checker.svg?branch=master)](https://travis-ci.com/mudkipmaster/chess.checker)

The repository contains a valid move generator designed to follow the following input and output scheme:

```
The input for the above board configuration should look something like this:
WHITE: Rf1, Kg1, Pf2, Ph2, Pg3
BLACK: Kb8, Ne8, Pa7, Pb7, Pc7, Ra5
PIECE TO MOVE: Rf1

Given the above input description, your program should produce the following output:
LEGAL MOVES FOR Rf1: e1, d1, c1, b1, a1

```

## Move Validation
The goal of this project was not performance but rather to be highly "dependable". To do this, move validation was done using a set subtraction system wherein each peices valid moves are a subset of every possible valid move. The following figure sets up this hieracy.

Image

The thought was that if move validation was treated as near a mathmatical problem as possible, it would be more easily tested and verified.
The test suit for this section of the program is actually reasonable simple. For each piece, a list of moves were generated that verified that each condition that made it different from it's parent was tested. For instance, A RookMove was defined as being able to make all Queen moves that are not Bishop moves. In the code this looks like this:

```
if (this.isValidBoardMove()) {
    if (this.isValidColoredMove(friendly)) {
        if (this.isValidSlideMove(friendly, foe)) {
            if (this.isValidQueenMove()) {
                if (this.isValidBishopMove()) {
                    return false;
                } else {
                    return true;
            }
        }
     }
}
```

We verify that this piece is valid if and only if by verifying that a valid can be made in valid by setting the return value of any of it's parent class' validation functions to false. Then we are left only to validate the moves that would be valid queen moves but not valid Rook moves. In this test class this looks like this (at the same time we test that the `toString()` method also returns the correct thing):
```
{3, 4, 6, 7, empty, empty, "Rh7", false},
{3, 4, 0, 7, empty, empty, "Rh1", false},
{3, 4, 7, 0, empty, empty, "Ra8", false},
{3, 4, 0, 1, empty, empty, "Rb1", false},

```
### Static Analysis
One of the rules that was set in the static analysis tool in Intellij was a warning for if statements with negate conditions. Initially I had several but, like the code snippet above shows, I reversed them. The resulting code is easier to read and less prone to mistakes. There are 6 remaining warning for this section. They are mostly warnings of violation of the Law of Demeter. The Law of Demeter states that 
> Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.

Intellij says I should refactor these methods and, given more time, I would definitely try. You can see the full result of the inspections [here](https://github.com/mudkipmaster/chess.checker/blob/1ba78a6a4b72bb21b0a96385f3e7deabe5a467c4/inspectionresults/staticanalysis/index.html)
### Code Coverage
There are 821 tests in the move validation suit.For the 

## Move Generation
While theorietically we could start with a prege
