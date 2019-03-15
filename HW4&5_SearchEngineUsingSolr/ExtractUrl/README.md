# Extract links from webpages

## Background

This is part of the 4th assignment for [USC CSCI 572: Information Retrieval and Web Search Engines](http://www-scf.usc.edu/~csci572/) in 2018 Spring.

[This single repo](https://github.com/dazingwill/ExtractUrl) in GitHub

[CSCI572 repo](https://github.com/dazingwill/CSCI572) with assignment description, grading guidelines and other data



Code has been slightly modified from the version I submitted.

## Objective

> Compute the incoming and outgoing links to the web pages, and create a NetworkX graph .

## Note

**Input files**

* *data/HTML Files* :

  Folder of all the webpages. This repo only contains 10 sample pages, full data can be found in refers.

* *data/UrlToHtml_Newday.csv* :

  Map file name to URL. Format:

  ```
  [file name], [URL]
  ```

**Output file**

* *data/edgeList.txt* : 

  Edges file prepared for PageRank. Format:

  ```
  [URL1] [URL2]
  ```

## Refers

Web Pages Data: https://drive.google.com/drive/folders/1ZPWjQZMB7xeegjaeZfzqeJKq9oKuukio 

