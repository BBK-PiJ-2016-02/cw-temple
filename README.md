# Coursework: Philip Hammond and the Temple of Gloom

Coursework for the [Programming in Java module](https://moodle.bbk.ac.uk/course/view.php?id=16449)  

[MSc Computer Science degree @ Birkbeck, University of London](http://dcs.bbk.ac.uk/study-with-us/postgraduate/msc-computer-science/)

## Exploration stage
For a quick initial solution I had the explorer, on each turn, walk in a random direction until they *eventually* bumped into the orb. Extremely wasteful, but allowed me to get a feel for the requirements (and a peek at the escape stage).

I then rewrote this to do a depth first search which is extremely faster in comparison.

I had plans to look into other solutions, for example I'd like to have seen if keeping track of the distance to the orb on each move (to get an idea of which direction to go in) would be useful, but I haven't had a chance to implement this as I became pressed for time while attempting to solve the escape stage.

## Escape stage
This stage took me a bit longer than I anticipated. I understood Dijkstra's Algorithm, but it didn't immediately click for me on how to apply it to a uniform maze. Eventually I realised I could just consider the distance between each node the same, and work from there.

I built the majority of the solution in `student.Graph` & `student.GraphNode`, later breaking out some of the functionality into a dedicated `student.RouteFinder` class.

## Further notes
I looked into implementing tests, however for most cases I wanted access to classes within the `game` namespace (e.g. `game.Node`) and could not due to package level restrictions. I'm sure there are many ways I could work around it (not quite ready/prepared to jump into mocking objects yet), but in the interest of time I decided to focus on implementing the solution.