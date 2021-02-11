import * as Linking from 'expo-linking';

export default {
  prefixes: [Linking.makeUrl('/')],
  config: {
    screens: {
      Root: {
        screens: {
          Sweepstake: {
            screens: {
              SweepstakeScreen: 'one',
            },
          },
          Settings: {
            screens: {
              SettingsScreen: 'two',
            },
          },
        },
      },
      NotFound: '*',
    },
  },
};
