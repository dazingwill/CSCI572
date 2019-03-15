<?php


require_once('Apache/Solr/Service.php');


header('Content-Type:application/json');

function sortSuggestions($a, $b)
{
    return $b->weight - $a->weight;
}

$suggestQuery = $_GET["term"];
//$solr = new Apache_Solr_Service('192.168.25.130', 8983, '/solr/cs572/');
$solr = new Apache_Solr_Service('localhost', 8983, '/solr/cs572/');

$suggestPrefix = "";
$lastSpace = strripos($suggestQuery, " ");
if ($lastSpace) {
    $suggestPrefix = substr($suggestQuery, 0, $lastSpace + 1);
    $suggestQuery = substr($suggestQuery, $lastSpace + 1);
}


try {
    $results = $solr->search($suggestQuery, 0, 10, array(), "GET", "suggest");
} catch (Exception $e) {
    // in production you'd probably log or email this error to an admin
    // and then show a special message to the user but for this example
    // we're going to show the full exception
    die("<html><head><title>SEARCH EXCEPTION</title><body><pre>{$e->__toString()}</pre></body></html>");
}
if ($results) {
    $results = current($results->suggest->suggest);

    $suggestNum = (int)$results->numFound;
    $suggestions = $results->suggestions;

    usort($suggestions, "sortSuggestions");

    $suggestionList = array();
    foreach ($suggestions as $suggestion) {
        $ss = $suggestPrefix . (string)$suggestion->term;
        if (preg_match("/[:\._-]/", $ss) != 1) {
            $suggestionList[] = $ss;
        }
    }
    $suggestionList = json_encode($suggestionList);

    echo $suggestionList;
} else {
    echo "no result";
}

