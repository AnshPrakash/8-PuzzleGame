# 8-PuzzleGame
Solves the 8 Puzzle Game
The game consists of a 3x3
grid with 8 tiles numbered 1 to 8. There is one gap in the puzzle that allows movement of tiles. Tiles can move
horizontally or vertically.
We will play a cost-version of the game. Here you will be given a cost function
described by 8 integers, d 1 ... d 8 , such that moving the tile numbered i will cost d i units. If d 1 = 1 and d 2 = 7, then you
can move 1 seven times to cost equal to moving 2 once. You will be given several test cases, and each test case will
consist of a start state, a goal state, and a cost function. Your task is to find the cheapest path to the goal. If multiple
paths exist with the same minimum cost, print the path with fewest moves. If multiple optimal paths to the goal exist
which take the fewest moves, then you can print any of them.
Code will be run using the following command:
javac Puzzle.java
java Puzzle someinputfilename.txt someoutputfilename.txt
Sample Input file is there in the repository.
