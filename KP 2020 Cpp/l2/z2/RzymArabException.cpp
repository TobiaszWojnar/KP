#include <stdexcept>
#include "RzymArabException.hpp"

using namespace std;

RzymArabException :: RzymArabException (const string & message): runtime_error(message) {}

