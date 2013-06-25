#ifndef CSVPARSER_H
#define CSVPARSER_H

#define DEBUG 0

#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <sstream>
#include <string>
#include <stdio.h>
#include <string.h>
#include "Utils.h"

using namespace std;

class CSVParser
{
    public:

        bool isFilled();
        void setFilled(bool isFilled);
        bool isNormalized();
        void setNormalized(bool isNormalized);
        vector < vector <double> > getValues();
        void debug();
        void normalizedDebug();
        double getValue(int x, int y);
        double getNormalizedValue(int x, int y);
        int countColumns();
        int countLines();
        double getMaxValueFromColumn(int y);
        void reload();
        void normalize();
        void transformIntoPercentage();
        CSVParser(string filename);
        CSVParser(string filename, int countLines, int countColumns, vector < vector <double> > values);
        CSVParser();
        ~CSVParser();
        void printResults();
        void addElementResultsColumn(int x, double value);

    private:

        bool _isFilled;
        bool _isNormalized;
        string _filename;
        char _separator;
        int _countLines;
        int _countColumns;
        double _values[Utils::MAX_LINES][Utils::MAX_COLUMNS];
        double _normalizedValues[Utils::MAX_LINES][Utils::MAX_COLUMNS];
};

#endif // CSVPARSER_H
