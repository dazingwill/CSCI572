# PageRank using NetworkX

## Background

This is part of the 4th assignment for [USC CSCI 572: Information Retrieval and Web Search Engines](http://www-scf.usc.edu/~csci572/) in 2018 Spring.

[CSCI572 repo](https://github.com/dazingwill/CSCI572) with assignment description, grading guidelines and other data



Code has been slightly modified from the version I submitted.

## Objective

> Compute the incoming and outgoing links to the web pages, and create a NetworkX graph .

## Note

A short python script simply call NetworkX's PageRank API and save the result.

**Input file**

* *edgeList.txt* : 

  Edges file prepared for PageRank. Format:

  ```
  [URL1] [URL2]
  ```

**Output file**

- *external_pageRankFile.txt* :

  a list of PageRank values for each URL, format:

  ```
  [webpage's path in solr server]=[PageRank value]
  ```

