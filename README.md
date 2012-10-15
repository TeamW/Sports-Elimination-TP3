Sports-Elimination-TP3
======================

Glasgow University Team Project (Team W) - A system for sports team eliminations.

Problem Specification

The primary aim of the project is to implement an 
algorithm which, given a sports league table and the remaining schedule 
of games, will determine which team(s) in the sports league have no possible 
chance of winning the league.
Most sports pundits make fairly trivial calculations to work this out. This 
is usually only based on the number of points team X can win versus the 
difference in points between team X and team Y (the team currently in first 
place), however a more complex analysis can give predictions earlier in the 
schedule than using the trivial approach.
We will be making use of the Ford-Fulkerson algorithm and network analysis 
to determine what teams have no possible chance of ending the league in 
first place. There are various routes which will force team X out of winning 
the league and a brute-force approach would be too costly when deciding whether 
X is eliminated. However utilizing the above algorithm leads to an operation 
time that is polynomial time based on the input size. We also plan, time permitting, 
to implement a statistical analysis on the non-eliminated teams to determine who 
has the highest probability of winning the league. Since the Ford-Fulkerson 
algorithm can only tell us who cannot win the league, this extension is a
 novel addition which could make for more interesting results. Baseball is 
 one of the most statistically analysed sports and these statistics can be 
 easily accessed, so the implementation will be the only main challenge here.