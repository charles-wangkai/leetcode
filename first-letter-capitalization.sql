SELECT
  content_id,
  content_text AS original_text,
  REPLACE(
    regexp_replace(
      REPLACE(
        regexp_replace(
          REPLACE(
            regexp_replace(
              REPLACE(
                regexp_replace(
                  REPLACE(
                    regexp_replace(
                      REPLACE(
                        regexp_replace(
                          REPLACE(
                            regexp_replace(
                              REPLACE(
                                regexp_replace(
                                  REPLACE(
                                    regexp_replace(
                                      REPLACE(
                                        regexp_replace(
                                          REPLACE(
                                            regexp_replace(
                                              REPLACE(
                                                regexp_replace(
                                                  REPLACE(
                                                    regexp_replace(
                                                      REPLACE(
                                                        regexp_replace(
                                                          REPLACE(
                                                            regexp_replace(
                                                              REPLACE(
                                                                regexp_replace(
                                                                  REPLACE(
                                                                    regexp_replace(
                                                                      REPLACE(
                                                                        regexp_replace(
                                                                          REPLACE(
                                                                            regexp_replace(
                                                                              REPLACE(
                                                                                regexp_replace(
                                                                                  REPLACE(
                                                                                    regexp_replace(
                                                                                      REPLACE(
                                                                                        regexp_replace(
                                                                                          REPLACE(
                                                                                            regexp_replace(
                                                                                              REPLACE(
                                                                                                regexp_replace(
                                                                                                  REPLACE(
                                                                                                    regexp_replace(
                                                                                                      REPLACE(
                                                                                                        regexp_replace(lower(content_text), '^a', 'A'),
                                                                                                        ' a',
                                                                                                        ' A'
                                                                                                      ),
                                                                                                      '^b',
                                                                                                      'B'
                                                                                                    ),
                                                                                                    ' b',
                                                                                                    ' B'
                                                                                                  ),
                                                                                                  '^c',
                                                                                                  'C'
                                                                                                ),
                                                                                                ' c',
                                                                                                ' C'
                                                                                              ),
                                                                                              '^d',
                                                                                              'D'
                                                                                            ),
                                                                                            ' d',
                                                                                            ' D'
                                                                                          ),
                                                                                          '^e',
                                                                                          'E'
                                                                                        ),
                                                                                        ' e',
                                                                                        ' E'
                                                                                      ),
                                                                                      '^f',
                                                                                      'F'
                                                                                    ),
                                                                                    ' f',
                                                                                    ' F'
                                                                                  ),
                                                                                  '^g',
                                                                                  'G'
                                                                                ),
                                                                                ' g',
                                                                                ' G'
                                                                              ),
                                                                              '^h',
                                                                              'H'
                                                                            ),
                                                                            ' h',
                                                                            ' H'
                                                                          ),
                                                                          '^i',
                                                                          'I'
                                                                        ),
                                                                        ' i',
                                                                        ' I'
                                                                      ),
                                                                      '^j',
                                                                      'J'
                                                                    ),
                                                                    ' j',
                                                                    ' J'
                                                                  ),
                                                                  '^k',
                                                                  'K'
                                                                ),
                                                                ' k',
                                                                ' K'
                                                              ),
                                                              '^l',
                                                              'L'
                                                            ),
                                                            ' l',
                                                            ' L'
                                                          ),
                                                          '^m',
                                                          'M'
                                                        ),
                                                        ' m',
                                                        ' M'
                                                      ),
                                                      '^n',
                                                      'N'
                                                    ),
                                                    ' n',
                                                    ' N'
                                                  ),
                                                  '^o',
                                                  'O'
                                                ),
                                                ' o',
                                                ' O'
                                              ),
                                              '^p',
                                              'P'
                                            ),
                                            ' p',
                                            ' P'
                                          ),
                                          '^q',
                                          'Q'
                                        ),
                                        ' q',
                                        ' Q'
                                      ),
                                      '^r',
                                      'R'
                                    ),
                                    ' r',
                                    ' R'
                                  ),
                                  '^s',
                                  'S'
                                ),
                                ' s',
                                ' S'
                              ),
                              '^t',
                              'T'
                            ),
                            ' t',
                            ' T'
                          ),
                          '^u',
                          'U'
                        ),
                        ' u',
                        ' U'
                      ),
                      '^v',
                      'V'
                    ),
                    ' v',
                    ' V'
                  ),
                  '^w',
                  'W'
                ),
                ' w',
                ' W'
              ),
              '^x',
              'X'
            ),
            ' x',
            ' X'
          ),
          '^y',
          'Y'
        ),
        ' y',
        ' Y'
      ),
      '^z',
      'Z'
    ),
    ' z',
    ' Z'
  ) AS converted_text
FROM
  user_content