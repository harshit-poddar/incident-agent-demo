# payments/handler.py  (excerpt)
from functools import lru_cache

@lru_cache(maxsize=10_000)
def handle_payment(req):
    # FIX: bound the cache so it can no longer grow unboundedly
    return build_receipt(req)
