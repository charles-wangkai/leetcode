import pandas as pd


def fillMissingValues(products: pd.DataFrame) -> pd.DataFrame:
    return products.fillna(value={"quantity": 0})
