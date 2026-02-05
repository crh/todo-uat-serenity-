export const sharedPlaywrightConfig = {
  use: {
    // Options: 'off', 'on', 'only-on-failure'
    screenshot: 'on',

    // Also highly recommended for UAT evidence:
    video: 'on-first-retry',
    trace: 'on',
    launchOptions: {
      chromiumSandbox: false,
      args: ['--no-sandbox', '--disable-setuid-sandbox'],
    },
  },
};
