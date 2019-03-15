# Solr Local Search Engine Site

## Background

This is part of the 4&5th assignment for [USC CSCI 572: Information Retrieval and Web Search Engines](http://www-scf.usc.edu/~csci572/) in 2018 Spring.

[This single repo](https://github.com/dazingwill/SolrSearchEngine) in GitHub

[CSCI572 repo](https://github.com/dazingwill/CSCI572) with assignment description, grading guidelines and other data



Code has been slightly modified from the version I submitted.

## Structure

* *big.txt* 

  The output of [TikaExtractText](https://github.com/dazingwill/TikaExtractText) .

* *SpellCorrector.php* 

  Norvig’s spell correction program in php.

* *serialized_dictionary.txt* 

  The output of *SpellCorrector.php*

* *index.php*

  Web page of the search engine

* *autocomplete.php*

  backend support of the auto complete feature.

* *Apache/*

  Solr API support in PHP.

## Refers

https://github.com/PTCInc/solr-php-client

http://norvig.com/spell-correct.html 

https://jqueryui.com/autocomplete/

https://lucene.apache.org/solr/guide/6_6/suggester.html 





