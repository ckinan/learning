# ckn-netlify-functions

Source code of examples developed in https://www.ckinan.com/blog/netlify-function

## Run locally:

```bash
# Download dependencies
npm install
# Run
npm run serve
```

## Test

- Classic hello world: http://localhost:9000/.netlify/functions/hello-world
- Async hello world: http://localhost:9000/.netlify/functions/hello-world-async
- Using environment variable: http://localhost:9000/.netlify/functions/env
- Fetch external API: http://localhost:9000/.netlify/functions/fetch

## Refs:

- https://github.com/netlify/netlify-lambda
- https://www.youtube.com/watch?v=nQEEW55iO7Q
- https://docs.netlify.com/configure-builds/file-based-configuration/#sample-file
- https://flaviocopes.com/netlify-functions/
- https://github.com/netlify/cli/blob/master/docs/netlify-dev.md#netlify-functions
