import { AppPage } from './app.po';
import 'jasmine'
import 'jasminewd2'

describe('angular-contafin App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
