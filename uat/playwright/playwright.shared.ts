export const sharedPlaywrightConfig = {
  use: {
    // Options: 'off', 'on', 'only-on-failure'
    screenshot: 'on',

    // Also highly recommended for UAT evidence:
    video: 'on-first-retry',
    trace: 'on',
    launchOptions: {
      chromiumSandbox: false,
      args: [
        '--no-sandbox',
        '--disable-setuid-sandbox',
        '--disable-dev-shm-usage',
        '--disable-gpu',
        '--disable-extensions',
        '--disable-software-rasterizer',
        '--disable-seccomp-filter-sandbox',
        '--remote-debugging-port=0',
      ],
    },
  },
};
