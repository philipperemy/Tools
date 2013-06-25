#ifndef SHRINKER_H
#define SHRINKER_H

#include "CSVParser.h"
#include <vector>
#include <iostream>

using namespace std;

class Shrinker
{
    public:
        Shrinker(CSVParser& csvparser);
        virtual ~Shrinker();
        double mean(vector<double> vec);
        int countLines();
        int countColumns();

    protected:
    private:
        double _shrinkedvalues[Utils::MAX_LINES][Utils::MAX_COLUMNS];
        int _period;
        int _countLines;
        int _countColumns;
};

#endif // SHRINKER_H
