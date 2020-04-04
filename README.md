# File Server Api
Api creada con compojure-api para cumplir con los requerimientos

Create a Clojurescript *Single Page Application* to display a file tree.

The root node for the tree should be specified by the client application.
Tree nodes should initially appear collapsed, with subtrees rendered after
expansion of the parent node.  

Any representation for the tree is valid (graphical, text based, ...) as 
long as it shows clearly the hierarchical relationships between tree nodes.
Additionally, the leaf nodes representing text files on disk should 
allow the user to display a preview of the file's content. 
The strategy for discriminating which files are suported for previewing 
is up to the developer.

You may
- Add any routes/actions to server
- Choose any set of server side operations to provide data to the client
- Use any data representation format for the information exchange
- Use any frotend framework for the cljs SPA
- Use any third party library

GreenPowerMonitor © 2018

## Run
lein ring server 4000